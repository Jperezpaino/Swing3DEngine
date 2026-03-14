package es.noa.rad.core.json;

 import
  java.io.IOException;

 class JsonLiteral extends JsonValue {
  private static final long serialVersionUID = 1L;
  private final String value;
  private final boolean isNull;
  private final boolean isTrue;
  private final boolean isFalse;

  public JsonLiteral(String value) {
   this.value = value;
   this.isNull = "null".equals(value);
   this.isTrue = "true".equals(value);
   this.isFalse = "false".equals(value);
  }

  @Override
  void write(JsonWriter writer) throws IOException {
   writer.writeLiteral(value);
  }

  @Override
  public String toString() {
    return value;
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  @Override
  public boolean isNull() {
    return isNull;
  }

  @Override
  public boolean isTrue() {
    return isTrue;
  }

  @Override
  public boolean isFalse() {
    return isFalse;
  }

  @Override
  public boolean isBoolean() {
    return isTrue || isFalse;
  }

  @Override
  public boolean asBoolean() {
    return isNull ? super.asBoolean() : isTrue;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (getClass() != object.getClass()) {
      return false;
    }
    JsonLiteral other = (JsonLiteral)object;
    return value.equals(other.value);
  }

}
