package com.daml.quickstart.model.daml.finance.interface$.lifecycle.rule.claim;

import com.daml.ledger.javaapi.data.DamlCollectors;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.daml.finance.interface$.settlement.batch.Batch;
import com.daml.quickstart.model.daml.finance.interface$.settlement.instruction.Instruction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClaimResult extends DamlRecord<ClaimResult> {
  public static final String _packageId = "20176559c50772de2d90e9779dcfad78bc0edf2a949454c8cb78dde8cb89f780";

  public final Batch.ContractId batchCid;

  public final List<Instruction.ContractId> instructionCids;

  public ClaimResult(Batch.ContractId batchCid, List<Instruction.ContractId> instructionCids) {
    this.batchCid = batchCid;
    this.instructionCids = instructionCids;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static ClaimResult fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<ClaimResult> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(2,
          recordValue$);
      Batch.ContractId batchCid =
          new Batch.ContractId(fields$.get(0).getValue().asContractId().orElseThrow(() -> new IllegalArgumentException("Expected batchCid to be of type com.daml.ledger.javaapi.data.ContractId")).getValue());
      List<Instruction.ContractId> instructionCids = PrimitiveValueDecoders.fromList(v$0 ->
              new Instruction.ContractId(v$0.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected instructionCids to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()))
          .decode(fields$.get(1).getValue());
      return new ClaimResult(batchCid, instructionCids);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(2);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("batchCid", this.batchCid.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("instructionCids", this.instructionCids.stream().collect(DamlCollectors.toDamlList(v$0 -> v$0.toValue()))));
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
    if (!(object instanceof ClaimResult)) {
      return false;
    }
    ClaimResult other = (ClaimResult) object;
    return Objects.equals(this.batchCid, other.batchCid) &&
        Objects.equals(this.instructionCids, other.instructionCids);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.batchCid, this.instructionCids);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.lifecycle.rule.claim.ClaimResult(%s, %s)",
        this.batchCid, this.instructionCids);
  }
}
