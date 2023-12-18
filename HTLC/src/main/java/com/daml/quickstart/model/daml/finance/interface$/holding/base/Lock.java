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

public class Lock extends DamlRecord<Lock> {
  public static final String _packageId = "95644d5c6ff8c9a433820d694916d86d5e94e1418880b66bf0b3e5103dbc0e09";

  public final Set<String> lockers;

  public final Set<String> context;

  public final LockType lockType;

  public Lock(Set<String> lockers, Set<String> context, LockType lockType) {
    this.lockers = lockers;
    this.context = context;
    this.lockType = lockType;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Lock fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Lock> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(3,
          recordValue$);
      Set<String> lockers = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(0).getValue());
      Set<String> context = Set.<String>valueDecoder(PrimitiveValueDecoders.fromText)
          .decode(fields$.get(1).getValue());
      LockType lockType = LockType.valueDecoder().decode(fields$.get(2).getValue());
      return new Lock(lockers, context, lockType);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(3);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("lockers", this.lockers.toValue(v$0 -> new Party(v$0))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("context", this.context.toValue(v$0 -> new Text(v$0))));
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
    if (!(object instanceof Lock)) {
      return false;
    }
    Lock other = (Lock) object;
    return Objects.equals(this.lockers, other.lockers) &&
        Objects.equals(this.context, other.context) &&
        Objects.equals(this.lockType, other.lockType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.lockers, this.context, this.lockType);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.holding.base.Lock(%s, %s, %s)",
        this.lockers, this.context, this.lockType);
  }
}
