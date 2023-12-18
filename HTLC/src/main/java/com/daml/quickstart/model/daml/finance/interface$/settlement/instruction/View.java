package com.daml.quickstart.model.daml.finance.interface$.settlement.instruction;

import com.daml.ledger.javaapi.data.DamlOptional;
import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Timestamp;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.Allocation;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.Approval;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.RoutedStep;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Id;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class View extends DamlRecord<View> {
  public static final String _packageId = "4b5bf7a6465e5e11800a03c131a5759565edd3ff9cfc43f41d1f7fbc72255587";

  public final Set<String> requestors;

  public final Set<String> settlers;

  public final Id batchId;

  public final Id id;

  public final RoutedStep routedStep;

  public final Optional<Instant> settlementTime;

  public final Allocation allocation;

  public final Approval approval;

  public final Set<String> signedSenders;

  public final Set<String> signedReceivers;

  public View(Set<String> requestors, Set<String> settlers, Id batchId, Id id,
      RoutedStep routedStep, Optional<Instant> settlementTime, Allocation allocation,
      Approval approval, Set<String> signedSenders, Set<String> signedReceivers) {
    this.requestors = requestors;
    this.settlers = settlers;
    this.batchId = batchId;
    this.id = id;
    this.routedStep = routedStep;
    this.settlementTime = settlementTime;
    this.allocation = allocation;
    this.approval = approval;
    this.signedSenders = signedSenders;
    this.signedReceivers = signedReceivers;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static View fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<View> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(10,
          recordValue$);
      Set<String> requestors = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(0).getValue());
      Set<String> settlers = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(1).getValue());
      Id batchId = Id.valueDecoder().decode(fields$.get(2).getValue());
      Id id = Id.valueDecoder().decode(fields$.get(3).getValue());
      RoutedStep routedStep = RoutedStep.valueDecoder().decode(fields$.get(4).getValue());
      Optional<Instant> settlementTime = PrimitiveValueDecoders.fromOptional(
            PrimitiveValueDecoders.fromTimestamp).decode(fields$.get(5).getValue());
      Allocation allocation = Allocation.valueDecoder().decode(fields$.get(6).getValue());
      Approval approval = Approval.valueDecoder().decode(fields$.get(7).getValue());
      Set<String> signedSenders =
          Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(8).getValue());
      Set<String> signedReceivers =
          Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(9).getValue());
      return new View(requestors, settlers, batchId, id, routedStep, settlementTime, allocation,
          approval, signedSenders, signedReceivers);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(10);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("requestors", this.requestors.toValue(v$0 -> new Party(v$0))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("settlers", this.settlers.toValue(v$0 -> new Party(v$0))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("batchId", this.batchId.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("id", this.id.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("routedStep", this.routedStep.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("settlementTime", DamlOptional.of(this.settlementTime.map(v$0 -> Timestamp.fromInstant(v$0)))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("allocation", this.allocation.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("approval", this.approval.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("signedSenders", this.signedSenders.toValue(v$0 -> new Party(v$0))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("signedReceivers", this.signedReceivers.toValue(v$0 -> new Party(v$0))));
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
    if (!(object instanceof View)) {
      return false;
    }
    View other = (View) object;
    return Objects.equals(this.requestors, other.requestors) &&
        Objects.equals(this.settlers, other.settlers) &&
        Objects.equals(this.batchId, other.batchId) && Objects.equals(this.id, other.id) &&
        Objects.equals(this.routedStep, other.routedStep) &&
        Objects.equals(this.settlementTime, other.settlementTime) &&
        Objects.equals(this.allocation, other.allocation) &&
        Objects.equals(this.approval, other.approval) &&
        Objects.equals(this.signedSenders, other.signedSenders) &&
        Objects.equals(this.signedReceivers, other.signedReceivers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.requestors, this.settlers, this.batchId, this.id, this.routedStep,
        this.settlementTime, this.allocation, this.approval, this.signedSenders,
        this.signedReceivers);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.settlement.instruction.View(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)",
        this.requestors, this.settlers, this.batchId, this.id, this.routedStep, this.settlementTime,
        this.allocation, this.approval, this.signedSenders, this.signedReceivers);
  }
}
