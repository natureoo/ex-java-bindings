package com.daml.quickstart.model.daml.finance.interface$.settlement.factory;

import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.RoutedStep;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Id;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Instruct extends DamlRecord<Instruct> {
  public static final String _packageId = "4b5bf7a6465e5e11800a03c131a5759565edd3ff9cfc43f41d1f7fbc72255587";

  public final Set<String> instructors;

  public final Set<String> settlers;

  public final Id id;

  public final String description;

  public final Optional<Id> contextId;

  public final List<RoutedStep> routedSteps;

  public final Optional<Instant> settlementTime;

  public Instruct(Set<String> instructors, Set<String> settlers, Id id, String description,
      Optional<Id> contextId, List<RoutedStep> routedSteps, Optional<Instant> settlementTime) {
    this.instructors = instructors;
    this.settlers = settlers;
    this.id = id;
    this.description = description;
    this.contextId = contextId;
    this.routedSteps = routedSteps;
    this.settlementTime = settlementTime;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Instruct fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Instruct> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(7,
          recordValue$);
      Set<String> instructors = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(0).getValue());
      Set<String> settlers = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(1).getValue());
      Id id = Id.valueDecoder().decode(fields$.get(2).getValue());
      String description = PrimitiveValueDecoders.fromText.decode(fields$.get(3).getValue());
      Optional<Id> contextId = PrimitiveValueDecoders.fromOptional(Id.valueDecoder())
          .decode(fields$.get(4).getValue());
      List<RoutedStep> routedSteps = PrimitiveValueDecoders.fromList(RoutedStep.valueDecoder())
          .decode(fields$.get(5).getValue());
      Optional<Instant> settlementTime = PrimitiveValueDecoders.fromOptional(
            PrimitiveValueDecoders.fromTimestamp).decode(fields$.get(6).getValue());
      return new Instruct(instructors, settlers, id, description, contextId, routedSteps,
          settlementTime);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(7);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("instructors", this.instructors.toValue(v$0 -> new Party(v$0))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("settlers", this.settlers.toValue(v$0 -> new Party(v$0))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("id", this.id.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("description", new Text(this.description)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("contextId", DamlOptional.of(this.contextId.map(v$0 -> v$0.toValue()))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("routedSteps", this.routedSteps.stream().collect(DamlCollectors.toDamlList(v$0 -> v$0.toValue()))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("settlementTime", DamlOptional.of(this.settlementTime.map(v$0 -> Timestamp.fromInstant(v$0)))));
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
    if (!(object instanceof Instruct)) {
      return false;
    }
    Instruct other = (Instruct) object;
    return Objects.equals(this.instructors, other.instructors) &&
        Objects.equals(this.settlers, other.settlers) && Objects.equals(this.id, other.id) &&
        Objects.equals(this.description, other.description) &&
        Objects.equals(this.contextId, other.contextId) &&
        Objects.equals(this.routedSteps, other.routedSteps) &&
        Objects.equals(this.settlementTime, other.settlementTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.instructors, this.settlers, this.id, this.description, this.contextId,
        this.routedSteps, this.settlementTime);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.settlement.factory.Instruct(%s, %s, %s, %s, %s, %s, %s)",
        this.instructors, this.settlers, this.id, this.description, this.contextId,
        this.routedSteps, this.settlementTime);
  }
}
