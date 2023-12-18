package com.daml.quickstart.model.workflow.transfer;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.daml.finance.interface$.holding.base.Base;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Accept extends DamlRecord<Accept> {
  public static final String _packageId = "91deeb52981e7064dbcf05e8dd2a81ef0d8a25fe4aaff70e56ec84fcb11aa28d";

  public final Base.ContractId holdingCid;

  public Accept(Base.ContractId holdingCid) {
    this.holdingCid = holdingCid;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Accept fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Accept> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(1,
          recordValue$);
      Base.ContractId holdingCid =
          new Base.ContractId(fields$.get(0).getValue().asContractId().orElseThrow(() -> new IllegalArgumentException("Expected holdingCid to be of type com.daml.ledger.javaapi.data.ContractId")).getValue());
      return new Accept(holdingCid);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(1);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("holdingCid", this.holdingCid.toValue()));
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
    if (!(object instanceof Accept)) {
      return false;
    }
    Accept other = (Accept) object;
    return Objects.equals(this.holdingCid, other.holdingCid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.holdingCid);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.workflow.transfer.Accept(%s)", this.holdingCid);
  }
}
