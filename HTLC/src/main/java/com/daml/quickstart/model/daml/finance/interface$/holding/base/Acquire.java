package com.daml.quickstart.model.daml.finance.interface$.holding.base;

import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Text;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.da.set.types.Set;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Acquire extends DamlRecord<Acquire> {
  public static final String _packageId = "95644d5c6ff8c9a433820d694916d86d5e94e1418880b66bf0b3e5103dbc0e09";

  public final Set<String> newLockers;

  public final String context;

  public final LockType lockType;

  public Acquire(Set<String> newLockers, String context, LockType lockType) {
    this.newLockers = newLockers;
    this.context = context;
    this.lockType = lockType;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Acquire fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Acquire> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(3,
          recordValue$);
      Set<String> newLockers = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(0).getValue());
      String context = PrimitiveValueDecoders.fromText.decode(fields$.get(1).getValue());
      LockType lockType = LockType.valueDecoder().decode(fields$.get(2).getValue());
      return new Acquire(newLockers, context, lockType);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(3);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("newLockers", this.newLockers.toValue(v$0 -> new Party(v$0))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("context", new Text(this.context)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("lockType", this.lockType.toValue()));
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
    if (!(object instanceof Acquire)) {
      return false;
    }
    Acquire other = (Acquire) object;
    return Objects.equals(this.newLockers, other.newLockers) &&
        Objects.equals(this.context, other.context) &&
        Objects.equals(this.lockType, other.lockType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.newLockers, this.context, this.lockType);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.holding.base.Acquire(%s, %s, %s)",
        this.newLockers, this.context, this.lockType);
  }
}
