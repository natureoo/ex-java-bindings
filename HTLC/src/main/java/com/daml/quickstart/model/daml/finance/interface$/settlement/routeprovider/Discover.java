package com.daml.quickstart.model.daml.finance.interface$.settlement.routeprovider;

import com.daml.ledger.javaapi.data.DamlCollectors;
import com.daml.ledger.javaapi.data.DamlOptional;
import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.Step;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Discover extends DamlRecord<Discover> {
  public static final String _packageId = "4b5bf7a6465e5e11800a03c131a5759565edd3ff9cfc43f41d1f7fbc72255587";

  public final Set<String> discoverors;

  public final Optional<Id> contextId;

  public final List<Step> steps;

  public Discover(Set<String> discoverors, Optional<Id> contextId, List<Step> steps) {
    this.discoverors = discoverors;
    this.contextId = contextId;
    this.steps = steps;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Discover fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Discover> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(3,
          recordValue$);
      Set<String> discoverors = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(0).getValue());
      Optional<Id> contextId = PrimitiveValueDecoders.fromOptional(Id.valueDecoder())
          .decode(fields$.get(1).getValue());
      List<Step> steps = PrimitiveValueDecoders.fromList(Step.valueDecoder())
          .decode(fields$.get(2).getValue());
      return new Discover(discoverors, contextId, steps);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(3);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("discoverors", this.discoverors.toValue(v$0 -> new Party(v$0))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("contextId", DamlOptional.of(this.contextId.map(v$0 -> v$0.toValue()))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("steps", this.steps.stream().collect(DamlCollectors.toDamlList(v$0 -> v$0.toValue()))));
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
    if (!(object instanceof Discover)) {
      return false;
    }
    Discover other = (Discover) object;
    return Objects.equals(this.discoverors, other.discoverors) &&
        Objects.equals(this.contextId, other.contextId) && Objects.equals(this.steps, other.steps);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.discoverors, this.contextId, this.steps);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.settlement.routeprovider.Discover(%s, %s, %s)",
        this.discoverors, this.contextId, this.steps);
  }
}
