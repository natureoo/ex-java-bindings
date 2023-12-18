package com.daml.quickstart.model.daml.finance.interface$.instrument.token.types;

import com.daml.ledger.javaapi.data.Text;
import com.daml.ledger.javaapi.data.Timestamp;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.InstrumentKey;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Token extends DamlRecord<Token> {
  public static final String _packageId = "30e810720119f9c9790bfcf623aab3f1fb9d3206b7c19ba26485a67f14b12c0c";

  public final InstrumentKey instrument;

  public final String description;

  public final Instant validAsOf;

  public Token(InstrumentKey instrument, String description, Instant validAsOf) {
    this.instrument = instrument;
    this.description = description;
    this.validAsOf = validAsOf;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Token fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Token> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(3,
          recordValue$);
      InstrumentKey instrument = InstrumentKey.valueDecoder().decode(fields$.get(0).getValue());
      String description = PrimitiveValueDecoders.fromText.decode(fields$.get(1).getValue());
      Instant validAsOf = PrimitiveValueDecoders.fromTimestamp.decode(fields$.get(2).getValue());
      return new Token(instrument, description, validAsOf);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(3);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("instrument", this.instrument.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("description", new Text(this.description)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("validAsOf", Timestamp.fromInstant(this.validAsOf)));
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
    if (!(object instanceof Token)) {
      return false;
    }
    Token other = (Token) object;
    return Objects.equals(this.instrument, other.instrument) &&
        Objects.equals(this.description, other.description) &&
        Objects.equals(this.validAsOf, other.validAsOf);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.instrument, this.description, this.validAsOf);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.instrument.token.types.Token(%s, %s, %s)",
        this.instrument, this.description, this.validAsOf);
  }
}
