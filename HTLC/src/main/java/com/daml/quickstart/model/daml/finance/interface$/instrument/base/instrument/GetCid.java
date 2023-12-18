package com.daml.quickstart.model.daml.finance.interface$.instrument.base.instrument;

import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GetCid extends DamlRecord<GetCid> {
  public static final String _packageId = "e42b454a2dc8f6726d45e36ee2b59e73d2cac95bded3be60ae3de9ac5a783e66";

  public final String viewer;

  public GetCid(String viewer) {
    this.viewer = viewer;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static GetCid fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<GetCid> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(1,
          recordValue$);
      String viewer = PrimitiveValueDecoders.fromParty.decode(fields$.get(0).getValue());
      return new GetCid(viewer);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(1);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("viewer", new Party(this.viewer)));
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
    if (!(object instanceof GetCid)) {
      return false;
    }
    GetCid other = (GetCid) object;
    return Objects.equals(this.viewer, other.viewer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.viewer);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.instrument.base.instrument.GetCid(%s)",
        this.viewer);
  }
}
