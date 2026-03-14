function Map(width, height, cell_size, canvas)
{
	//-- init
	this.width=width || 10;
	this.height=height || 10;
	this.cell_size=cell_size || 50;
	canvas.width=this.width*this.cell_size;
	canvas.height=this.height*this.cell_size;
	this.ctx=canvas.getContext("2d");
	this.origin=0x0;
	this.destiny=0x0;
	//-- inside globals
	var map=this;
	var ctx=this.ctx;
	//-- members
	this.cells=[];
	var terrains=[
		{awkwardness:1.00, color:"#6DBF50", name:"ground"},
		{awkwardness:0.75, color:"#1F7F3A", name:"forest"},
		{awkwardness:0.50, color:"#6FD1FF", name:"water"},
		{awkwardness:0.10, color:"#2B427D", name:"deep_water"},
		{awkwardness:0.00, color:"#26221E", name:"wall"},
		{awkwardness:0.90, color:"#76421E", name:"bridge"}
	];
	// -- allow external access by name (like map.terrain.water...)
	(function indexTerrains()
	{
		map.terrains={};
		for(var i=0;i<terrains.length;i++)
			map.terrains[terrains[i].name]=terrains[i];
	}());
	//-- utils
	this.randTerrain=function()
	{
		var index=Math.floor(Math.random()*terrains.length-0.2);
		return terrains[index>0?index:0];
	}
	this.drawFlag=function(x,y,color)
	{
		var x=x*this.cell_size+this.cell_size/2;
		var y=y*this.cell_size+this.cell_size/2;
		ctx.save();
		ctx.fillStyle=color;
		ctx.strokeStyle="#000";
		ctx.beginPath();
		ctx.arc(x,y,this.cell_size/3,0,2*Math.PI);
		ctx.stroke();
		ctx.fill();
		ctx.restore();
	}
	//-- private classes
	function Cell(x, y)
	{
		this.G="";
		this.H="";
		this.F=this.G+this.H;
		this.x=x;
		this.y=y;
		this.list=0x0;
		this.dad=0x0;
		this.terrain=map.randTerrain();
		this.drawDad=function(x,y)
		{
			ctx.save();
			ctx.fillStyle="#4D92FF";
			ctx.strokeStyle="#000";
			ctx.beginPath();
			ctx.arc(x,y,map.cell_size/10,0,2*Math.PI);
			ctx.stroke();
			ctx.fill();
			ctx.restore();
		}
		this.render=function()
		{
			ctx.save();
			ctx.fillStyle=this.terrain.color;
			ctx.strokeStyle="#000";
			ctx.fillRect(0,0,map.cell_size, map.cell_size);
			ctx.strokeRect(0,0,map.cell_size, map.cell_size);
			//Draw data
			ctx.fillStyle="#000";
			ctx.font = "10px Arial";
			ctx.textAlign="start";
			ctx.textBaseline="top";
			ctx.fillText(this.F,1,1);
			ctx.textBaseline="bottom";
			ctx.fillText(this.G,1,map.cell_size);
			ctx.textAlign="end";
			ctx.fillText(this.H,map.cell_size-1,map.cell_size);
			//Draw list
			if(this.list)
			{
				ctx.strokeStyle=this.list=="open"?"#F00":"#a0F";
				ctx.strokeRect(1,1,map.cell_size-2, map.cell_size-2);
			}
			//Draw dad
			if(this.dad)
			{
				var offset=(map.cell_size/10)*3;
				var x=offset;
				var y=offset;
				if(this.dad.x==this.x)
					x=map.cell_size/2;
				if(this.dad.x>this.x)
					x=map.cell_size-offset;
				if(this.dad.y==this.y)
					y=map.cell_size/2;
				if(this.dad.y>this.y)
					y=map.cell_size-offset;
				this.drawDad(x,y);
			}	
			ctx.restore();

		}
	}
	//-- constructor
	for(var i=0;i<this.width;i++)
	{
		this.cells[i]=[];
		for(var j=0;j<this.height;j++)
			this.cells[i].push(new Cell(i,j));
	}	
	//--methods
	this.render=function()
	{
		for(var i=0;i<this.width;i++)
		{
			for(var j=0;j<this.height;j++)
			{
				ctx.save();
				ctx.translate(i*this.cell_size,j*this.cell_size);
				this.cells[i][j].render();
				ctx.restore();
			}
		}
		if(this.origin)
			this.drawFlag(this.origin.x,this.origin.y,"#A65C3E");
		if(this.destiny)
			this.drawFlag(this.destiny.x,this.destiny.y,"#A92535");
	}
	this.fill=function(terrain, skip_render)
	{
		for(var i=0;i<this.width;i++)
			for(var j=0;j<this.height;j++)
				this.cells[i][j].terrain=terrain;
		if(!skip_render)
			this.render();
	}
	this.rect=function(terrain, x, y, w, h, skip_render)
	{
		for(var i=x;i<x+w;i++)
			for(var j=y;j<y+h;j++)
				this.cells[i][j].terrain=terrain;
		if(!skip_render)
			this.render();
	}
	this.addTerrain=function(terrain)
	{
		terrains.push(terrain);
		indexTerrains();
	}
	this.getAdjacentCells=function(cell)
	{
		var adjacent_cells=[];
		for(var i=cell.x-1;i<=cell.x+1;i++)
		{
			for(var j=cell.y-1;j<=cell.y+1;j++)
			{
				try
				{
					if(this.cells[i][j] && !(i==cell.x && j==cell.y))
						adjacent_cells.push(this.cells[i][j]);
				}
				catch(ex) {}
			}
		}
		return adjacent_cells;
	}
}

function Pathfinding(map)
{
	//OpenList class
	function OpenList()
	{
		var cells=[];
		this.add=function(cell)
		{
			cell.list="open";
			cells.push(cell);
		}
		this.getFirst=function()
		{
			var first=cells.splice(0,1)[0];
			return first;
		}
		this.sort=function()
		{
			cells.sort(function(a,b) {return a.F-b.F;});
		}
		this.contains=function(cell)
		{
			return cells.indexOf(cell)<0?false:true;
		}
	}
	//ClosedList class
	function ClosedList()
	{
		var cells=[];
		this.add=function(cell)
		{
			cell.list="closed";
			cells.push(cell);
		}
		this.contains=function(cell)
		{
			return cells.indexOf(cell)<0?false:true;
		}
	}
	//init
	var open_list=new OpenList();
	var closed_list=new ClosedList();
	var ctx=map.ctx;
	open_list.add(map.cells[map.origin.x][map.origin.y]);
	this.ended=false;
	this.iterations=0;
	//members
	this.next=function(skip_render)
	{
		if(this.ended)
			return;
		this.interations++;
		//get the first cell
		var dad=open_list.getFirst();
		closed_list.add(dad);
		//add the adjacents cells to the open list
		var adjacent_cells=map.getAdjacentCells(dad);
		for(var i=0;i<adjacent_cells.length;i++)
		{
			var cell=adjacent_cells[i];
			//~~~ Start check end
			if(cell.x==map.destiny.x && cell.y==map.destiny.y)
			{
				this.ended=true;
				cell.dad=dad;
				if(!skip_render)
					this.drawPath(cell);
				return cell;
			}
			//~~~ End check end
			//~~~ Start of the exclusion zone
			if(!cell.terrain.awkwardness)
				continue;
			if(closed_list.contains(cell))
				continue;
			if(open_list.contains(cell))
			{
				var awk=cell.terrain.awkwardness;
				//Change the dad and G,H,F if the current path is better
				var newG=parseInt(cell.x!=dad.x && cell.y!=dad.y? dad.G+14/awk : dad.G+10/awk);
				if(cell.G<newG) //worse? ok, skip
					continue;
			}
			//~~~ End of the exclusion zone
			one_change_at_least=true;
			cell.dad=dad;
			//Length to the origin
			var awk=cell.terrain.awkwardness;
			cell.G=Math.round(parseInt(cell.x!=dad.x && cell.y!=dad.y? dad.G+14/awk : dad.G+10/awk));
			//Manhattan heuristic
			var dx=Math.abs(map.destiny.x-cell.x);
			var dy=Math.abs(map.destiny.y-cell.y);
			cell.H=(dx+dy)*10;
			//Update F
			cell.F=cell.G+cell.H;
			open_list.add(cell);
		}
		//sort open list
		open_list.sort();
		//render
		if(!skip_render)
			map.render();
	}
	this.drawPath=function(cell)
	{
		var offset=map.cell_size/2;
		var next=cell.dad;
		ctx.save();
		while(next)
		{
			ctx.moveTo(cell.x*map.cell_size+offset,cell.y*map.cell_size+offset)
			ctx.lineTo(next.x*map.cell_size+offset,next.y*map.cell_size+offset);
			ctx.stroke();
			cell=next;
			next=cell.dad;
		}
		ctx.restore();
	}
	this.solve=function()
	{
		var last_cell=0x0;
		while(!this.ended)
			last_cell=this.next(true);
		map.render();
		this.drawPath(last_cell);
	}
}