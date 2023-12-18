package com.daml.quickstart.model.daml.finance.interface$.lifecycle.event.distribution;

import com.daml.ledger.javaapi.data.DamlCollectors;
import com.daml.ledger.javaapi.data.Numeric;
import com.daml.ledger.javaapi.data.Timestamp;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.InstrumentKey;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Quantity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class View extends DamlRecord<View> {
  public static final String _packageId = "20176559c50772de2d90e9779dcfad78bc0edf2a949454c8cb78dde8cb89f780";

  public final Instant effectiveTime;

  public final InstrumentKey targetInstrument;

  public final InstrumentKey newInstrument;

  public final List<Quantity<InstrumentKey, BigDecimal>> perUnitDistribution;

  public View(Instant effectiveTime, InstrumentKey targetInstrument, InstrumentKey newInstrument,
      List<Quantity<InstrumentKey, BigDecimal>> perUnitDistribution) {
    this.effectiveTime = effectiveTime;
    this.targetInstrument = targetInstrument;
    this.newInstrument = newInstrument;
    this.perUnitDistribution = perUnitDistribution;
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
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(4,
          recordValue$);
      Instant effectiveTime = PrimitiveValueDecoders.fromTimestamp
          .decode(fields$.get(0).getValue());
      InstrumentKey targetInstrument = InstrumentKey.valueDecoder()
          .decode(fields$.get(1).getValue());
      InstrumentKey newInstrument = InstrumentKey.valueDecoder().decode(fields$.get(2).getValue());
      List<Quantity<InstrumentKey, BigDecimal>> perUnitDistribution =
          PrimitiveValueDecoders.fromList(
            Quantity.<InstrumentKey,
            BigDecimal>valueDecoder(InstrumentKey.valueDecoder(),
            PrimitiveValueDecoders.fromNumeric)).decode(fields$.get(3).getValue());
      return new View(effectiveTime, targetInstrument, newInstrument, perUnitDistribution);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(4);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("effectiveTime", Timestamp.fromInstant(this.effectiveTime)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("targetInstrument", this.targetInstrument.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("newInstrument", this.newInstrument.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("perUnitDistribution", this.perUnitDistribution.stream().collect(DamlCollectors.toDamlList(v$0 -> v$0.toValue(v$1 -> v$1.toValue(),
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
    return Objects.equals(this.effectiveTime, other.effectiveTime) &&
        Objects.equals(this.targetInstrument, other.targetInstrument) &&
        Objects.equals(this.newInstrument, other.newInstrument) &&
        Objects.equals(this.perUnitDistribution, other.perUnitDistribution);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.effectiveTime, this.targetInstrument, this.newInstrument,
        this.perUnitDistribution);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.lifecycle.event.distribution.View(%s, %s, %s, %s)",
        this.effectiveTime, this.targetInstrument, this.newInstrument, this.perUnitDistribution);
  }
}
