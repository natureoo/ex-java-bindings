package com.daml.quickstart.model.daml.finance.settlement.batch;

import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BatchKey extends DamlRecord<BatchKey> {
  public static final String _packageId = "252a8b6233ce5a616d582dc0b88449a7a11f5d03bf9709570f02ef8e6c28be94";

  public final Set<String> requestors;

  public final Id id;

  public BatchKey(Set<String> requestors, Id id) {
    this.requestors = requestors;
    this.id = id;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static BatchKey fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<BatchKey> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(2,
          recordValue$);
      Set<String> requestors = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(0).getValue());
      Id id = Id.valueDecoder().decode(fields$.get(1).getValue());
      return new BatchKey(requestors, id);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(2);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("requestors", this.requestors.toValue(v$0 -> new Party(v$0))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("id", this.id.toValue()));
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
    if (!(object instanceof BatchKey)) {
      return false;
    }
    BatchKey other = (BatchKey) object;
    return Objects.equals(this.requestors, other.requestors) && Objects.equals(this.id, other.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.requestors, this.id);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.settlement.batch.BatchKey(%s, %s)",
        this.requestors, this.id);
  }
}
