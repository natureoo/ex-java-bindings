package com.daml.quickstart.model.daml.finance.interface$.lifecycle.rule.claim;

import com.daml.ledger.javaapi.data.DamlCollectors;
import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.daml.finance.interface$.holding.base.Base;
import com.daml.quickstart.model.daml.finance.interface$.lifecycle.effect.Effect;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClaimEffect extends DamlRecord<ClaimEffect> {
  public static final String _packageId = "20176559c50772de2d90e9779dcfad78bc0edf2a949454c8cb78dde8cb89f780";

  public final String claimer;

  public final List<Base.ContractId> holdingCids;

  public final Effect.ContractId effectCid;

  public final Id batchId;

  public ClaimEffect(String claimer, List<Base.ContractId> holdingCids, Effect.ContractId effectCid,
      Id batchId) {
    this.claimer = claimer;
    this.holdingCids = holdingCids;
    this.effectCid = effectCid;
    this.batchId = batchId;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static ClaimEffect fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<ClaimEffect> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(4,
          recordValue$);
      String claimer = PrimitiveValueDecoders.fromParty.decode(fields$.get(0).getValue());
      List<Base.ContractId> holdingCids = PrimitiveValueDecoders.fromList(v$0 ->
              new Base.ContractId(v$0.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected holdingCids to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()))
          .decode(fields$.get(1).getValue());
      Effect.ContractId effectCid =
          new Effect.ContractId(fields$.get(2).getValue().asContractId().orElseThrow(() -> new IllegalArgumentException("Expected effectCid to be of type com.daml.ledger.javaapi.data.ContractId")).getValue());
      Id batchId = Id.valueDecoder().decode(fields$.get(3).getValue());
      return new ClaimEffect(claimer, holdingCids, effectCid, batchId);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(4);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("claimer", new Party(this.claimer)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("holdingCids", this.holdingCids.stream().collect(DamlCollectors.toDamlList(v$0 -> v$0.toValue()))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("effectCid", this.effectCid.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("batchId", this.batchId.toValue()));
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
    if (!(object instanceof ClaimEffect)) {
      return false;
    }
    ClaimEffect other = (ClaimEffect) object;
    return Objects.equals(this.claimer, other.claimer) &&
        Objects.equals(this.holdingCids, other.holdingCids) &&
        Objects.equals(this.effectCid, other.effectCid) &&
        Objects.equals(this.batchId, other.batchId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.claimer, this.holdingCids, this.effectCid, this.batchId);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.lifecycle.rule.claim.ClaimEffect(%s, %s, %s, %s)",
        this.claimer, this.holdingCids, this.effectCid, this.batchId);
  }
}
