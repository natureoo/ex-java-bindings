package com.daml.quickstart.model.daml.finance.interface$.types.common.types;

import com.daml.ledger.javaapi.data.Text;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Id extends DamlRecord<Id> {
  public static final String _packageId = "361a5742b581d2d0dc1378f905a7f0ff692d3eb0d79b9fde51e34390813cf7d4";

  public final String unpack;

  public Id(String unpack) {
    this.unpack = unpack;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Id fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Id> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(1,
          recordValue$);
      String unpack = PrimitiveValueDecoders.fromText.decode(fields$.get(0).getValue());
      return new Id(unpack);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(1);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("unpack", new Text(this.unpack)));
    return new com.daml.ledger.javaapi.data.DamlRecord(fields);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (!(object instanceof Id)) {
      return false;
    }
    Id other = (Id) object;
    return Objects.equals(this.unpack, other.unpack);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.unpack);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.types.common.types.Id(%s)",
        this.unpack);
  }
}
