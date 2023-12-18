package com.daml.quickstart.model.daml.finance.interface$.holding.factory;

import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.AccountKey;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.InstrumentKey;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Create extends DamlRecord<Create> {
  public static final String _packageId = "95644d5c6ff8c9a433820d694916d86d5e94e1418880b66bf0b3e5103dbc0e09";

  public final InstrumentKey instrument;

  public final AccountKey account;

  public final BigDecimal amount;

  public final Map<String, Set<String>> observers;

  public Create(InstrumentKey instrument, AccountKey account, BigDecimal amount,
      Map<String, Set<String>> observers) {
    this.instrument = instrument;
    this.account = account;
    this.amount = amount;
    this.observers = observers;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Create fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Create> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(4,
          recordValue$);
      InstrumentKey instrument = InstrumentKey.valueDecoder().decode(fields$.get(0).getValue());
      AccountKey account = AccountKey.valueDecoder().decode(fields$.get(1).getValue());
      BigDecimal amount = PrimitiveValueDecoders.fromNumeric.decode(fields$.get(2).getValue());
      Map<String, Set<String>> observers = PrimitiveValueDecoders.fromGenMap(
            PrimitiveValueDecoders.fromText,
            Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty))
          .decode(fields$.get(3).getValue());
      return new Create(instrument, account, amount, observers);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(4);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("instrument", this.instrument.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("account", this.account.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("amount", new Numeric(this.amount)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("observers", this.observers.entrySet()
        .stream()
        .collect(DamlCollectors.toDamlGenMap(v$0 -> new Text(v$0.getKey()), v$0 -> v$0.getValue().toValue(v$1 -> new Party(v$1))))));
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
    if (!(object instanceof Create)) {
      return false;
    }
    Create other = (Create) object;
    return Objects.equals(this.instrument, other.instrument) &&
        Objects.equals(this.account, other.account) && Objects.equals(this.amount, other.amount) &&
        Objects.equals(this.observers, other.observers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.instrument, this.account, this.amount, this.observers);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.holding.factory.Create(%s, %s, %s, %s)",
        this.instrument, this.account, this.amount, this.observers);
  }
}
