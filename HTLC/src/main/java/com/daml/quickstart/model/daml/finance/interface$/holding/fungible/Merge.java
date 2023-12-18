package com.daml.quickstart.model.daml.finance.interface$.holding.fungible;

import com.daml.ledger.javaapi.data.DamlCollectors;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Merge extends DamlRecord<Merge> {
  public static final String _packageId = "95644d5c6ff8c9a433820d694916d86d5e94e1418880b66bf0b3e5103dbc0e09";

  public final List<Fungible.ContractId> fungibleCids;

  public Merge(List<Fungible.ContractId> fungibleCids) {
    this.fungibleCids = fungibleCids;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Merge fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Merge> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(1,
          recordValue$);
      List<Fungible.ContractId> fungibleCids = PrimitiveValueDecoders.fromList(v$0 ->
              new Fungible.ContractId(v$0.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected fungibleCids to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()))
          .decode(fields$.get(0).getValue());
      return new Merge(fungibleCids);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(1);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("fungibleCids", this.fungibleCids.stream().collect(DamlCollectors.toDamlList(v$0 -> v$0.toValue()))));
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
    if (!(object instanceof Merge)) {
      return false;
    }
    Merge other = (Merge) object;
    return Objects.equals(this.fungibleCids, other.fungibleCids);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.fungibleCids);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.holding.fungible.Merge(%s)",
        this.fungibleCids);
  }
}
