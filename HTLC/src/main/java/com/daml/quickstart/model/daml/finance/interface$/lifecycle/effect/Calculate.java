package com.daml.quickstart.model.daml.finance.interface$.lifecycle.effect;

import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.daml.finance.interface$.holding.base.Base;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Calculate extends DamlRecord<Calculate> {
  public static final String _packageId = "20176559c50772de2d90e9779dcfad78bc0edf2a949454c8cb78dde8cb89f780";

  public final String actor;

  public final Base.ContractId holdingCid;

  public Calculate(String actor, Base.ContractId holdingCid) {
    this.actor = actor;
    this.holdingCid = holdingCid;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Calculate fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Calculate> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(2,
          recordValue$);
      String actor = PrimitiveValueDecoders.fromParty.decode(fields$.get(0).getValue());
      Base.ContractId holdingCid =
          new Base.ContractId(fields$.get(1).getValue().asContractId().orElseThrow(() -> new IllegalArgumentException("Expected holdingCid to be of type com.daml.ledger.javaapi.data.ContractId")).getValue());
      return new Calculate(actor, holdingCid);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(2);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("actor", new Party(this.actor)));
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
    if (!(object instanceof Calculate)) {
      return false;
    }
    Calculate other = (Calculate) object;
    return Objects.equals(this.actor, other.actor) &&
        Objects.equals(this.holdingCid, other.holdingCid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.actor, this.holdingCid);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.lifecycle.effect.Calculate(%s, %s)",
        this.actor, this.holdingCid);
  }
}
