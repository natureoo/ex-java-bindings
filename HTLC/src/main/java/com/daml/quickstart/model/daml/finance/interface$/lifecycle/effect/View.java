package com.daml.quickstart.model.daml.finance.interface$.lifecycle.effect;

import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Id;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.InstrumentKey;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Quantity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class View extends DamlRecord<View> {
  public static final String _packageId = "20176559c50772de2d90e9779dcfad78bc0edf2a949454c8cb78dde8cb89f780";

  public final Set<String> providers;

  public final InstrumentKey targetInstrument;

  public final Optional<InstrumentKey> producedInstrument;

  public final Id id;

  public final String description;

  public final Optional<Instant> settlementTime;

  public final List<Quantity<InstrumentKey, BigDecimal>> otherConsumed;

  public final List<Quantity<InstrumentKey, BigDecimal>> otherProduced;

  public View(Set<String> providers, InstrumentKey targetInstrument,
      Optional<InstrumentKey> producedInstrument, Id id, String description,
      Optional<Instant> settlementTime, List<Quantity<InstrumentKey, BigDecimal>> otherConsumed,
      List<Quantity<InstrumentKey, BigDecimal>> otherProduced) {
    this.providers = providers;
    this.targetInstrument = targetInstrument;
    this.producedInstrument = producedInstrument;
    this.id = id;
    this.description = description;
    this.settlementTime = settlementTime;
    this.otherConsumed = otherConsumed;
    this.otherProduced = otherProduced;
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
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(8,
          recordValue$);
      Set<String> providers = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(0).getValue());
      InstrumentKey targetInstrument = InstrumentKey.valueDecoder()
          .decode(fields$.get(1).getValue());
      Optional<InstrumentKey> producedInstrument = PrimitiveValueDecoders.fromOptional(
            InstrumentKey.valueDecoder()).decode(fields$.get(2).getValue());
      Id id = Id.valueDecoder().decode(fields$.get(3).getValue());
      String description = PrimitiveValueDecoders.fromText.decode(fields$.get(4).getValue());
      Optional<Instant> settlementTime = PrimitiveValueDecoders.fromOptional(
            PrimitiveValueDecoders.fromTimestamp).decode(fields$.get(5).getValue());
      List<Quantity<InstrumentKey, BigDecimal>> otherConsumed = PrimitiveValueDecoders.fromList(
            Quantity.<InstrumentKey,
            BigDecimal>valueDecoder(InstrumentKey.valueDecoder(),
            PrimitiveValueDecoders.fromNumeric)).decode(fields$.get(6).getValue());
      List<Quantity<InstrumentKey, BigDecimal>> otherProduced = PrimitiveValueDecoders.fromList(
            Quantity.<InstrumentKey,
            BigDecimal>valueDecoder(InstrumentKey.valueDecoder(),
            PrimitiveValueDecoders.fromNumeric)).decode(fields$.get(7).getValue());
      return new View(providers, targetInstrument, producedInstrument, id, description,
          settlementTime, otherConsumed, otherProduced);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(8);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("providers", this.providers.toValue(v$0 -> new Party(v$0))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("targetInstrument", this.targetInstrument.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("producedInstrument", DamlOptional.of(this.producedInstrument.map(v$0 -> v$0.toValue()))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("id", this.id.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("description", new Text(this.description)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("settlementTime", DamlOptional.of(this.settlementTime.map(v$0 -> Timestamp.fromInstant(v$0)))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("otherConsumed", this.otherConsumed.stream().collect(DamlCollectors.toDamlList(v$0 -> v$0.toValue(v$1 -> v$1.toValue(),
        v$2 -> new Numeric(v$2))))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("otherProduced", this.otherProduced.stream().collect(DamlCollectors.toDamlList(v$0 -> v$0.toValue(v$1 -> v$1.toValue(),
        v$2 -> new Numeric(v$2))))));
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
    return Objects.equals(this.providers, other.providers) &&
        Objects.equals(this.targetInstrument, other.targetInstrument) &&
        Objects.equals(this.producedInstrument, other.producedInstrument) &&
        Objects.equals(this.id, other.id) && Objects.equals(this.description, other.description) &&
        Objects.equals(this.settlementTime, other.settlementTime) &&
        Objects.equals(this.otherConsumed, other.otherConsumed) &&
        Objects.equals(this.otherProduced, other.otherProduced);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.providers, this.targetInstrument, this.producedInstrument, this.id,
        this.description, this.settlementTime, this.otherConsumed, this.otherProduced);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.lifecycle.effect.View(%s, %s, %s, %s, %s, %s, %s, %s)",
        this.providers, this.targetInstrument, this.producedInstrument, this.id, this.description,
        this.settlementTime, this.otherConsumed, this.otherProduced);
  }
}
