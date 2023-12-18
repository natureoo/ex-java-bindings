package com.daml.quickstart.model.daml.finance.interface$.holding.fungible;

import com.daml.ledger.javaapi.data.DamlCollectors;
import com.daml.ledger.javaapi.data.DamlOptional;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SplitResult extends DamlRecord<SplitResult> {
  public static final String _packageId = "95644d5c6ff8c9a433820d694916d86d5e94e1418880b66bf0b3e5103dbc0e09";

  public final List<Fungible.ContractId> splitCids;

  public final Optional<Fungible.ContractId> rest;

  public SplitResult(List<Fungible.ContractId> splitCids, Optional<Fungible.ContractId> rest) {
    this.splitCids = splitCids;
    this.rest = rest;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static SplitResult fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<SplitResult> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(2,
          recordValue$);
      List<Fungible.ContractId> splitCids = PrimitiveValueDecoders.fromList(v$0 ->
              new Fungible.ContractId(v$0.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected splitCids to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()))
          .decode(fields$.get(0).getValue());
      Optional<Fungible.ContractId> rest = PrimitiveValueDecoders.fromOptional(v$0 ->
              new Fungible.ContractId(v$0.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected rest to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()))
          .decode(fields$.get(1).getValue());
      return new SplitResult(splitCids, rest);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(2);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("splitCids", this.splitCids.stream().collect(DamlCollectors.toDamlList(v$0 -> v$0.toValue()))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("rest", DamlOptional.of(this.rest.map(v$0 -> v$0.toValue()))));
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
    if (!(object instanceof SplitResult)) {
      return false;
    }
    SplitResult other = (SplitResult) object;
    return Objects.equals(this.splitCids, other.splitCids) && Objects.equals(this.rest, other.rest);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.splitCids, this.rest);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.holding.fungible.SplitResult(%s, %s)",
        this.splitCids, this.rest);
  }
}
