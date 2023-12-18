package com.daml.quickstart.model.daml.finance.interface$.instrument.base.instrument;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SetCid extends DamlRecord<SetCid> {
  public static final String _packageId = "e42b454a2dc8f6726d45e36ee2b59e73d2cac95bded3be60ae3de9ac5a783e66";

  public final Instrument.ContractId newCid;

  public SetCid(Instrument.ContractId newCid) {
    this.newCid = newCid;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static SetCid fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<SetCid> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(1,
          recordValue$);
      Instrument.ContractId newCid =
          new Instrument.ContractId(fields$.get(0).getValue().asContractId().orElseThrow(() -> new IllegalArgumentException("Expected newCid to be of type com.daml.ledger.javaapi.data.ContractId")).getValue());
      return new SetCid(newCid);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(1);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("newCid", this.newCid.toValue()));
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
    if (!(object instanceof SetCid)) {
      return false;
    }
    SetCid other = (SetCid) object;
    return Objects.equals(this.newCid, other.newCid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.newCid);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.instrument.base.instrument.SetCid(%s)",
        this.newCid);
  }
}
