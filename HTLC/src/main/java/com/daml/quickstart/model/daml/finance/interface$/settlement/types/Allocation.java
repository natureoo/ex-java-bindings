package com.daml.quickstart.model.daml.finance.interface$.settlement.types;

import com.daml.ledger.javaapi.data.Unit;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.ledger.javaapi.data.codegen.Variant;
import com.daml.quickstart.model.da.types.Tuple2;
import com.daml.quickstart.model.daml.finance.interface$.holding.base.Base;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.allocation.*;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.AccountKey;

public abstract class Allocation extends Variant<Allocation> {
  public static final String _packageId = "4b5bf7a6465e5e11800a03c131a5759565edd3ff9cfc43f41d1f7fbc72255587";

  public Allocation() {
  }

  public abstract com.daml.ledger.javaapi.data.Variant toValue();

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Allocation fromValue(Value value$) {
    com.daml.ledger.javaapi.data.Variant variant$ = value$.asVariant().orElseThrow(() -> new IllegalArgumentException("Expected Variant to build an instance of the Variant com.daml.quickstart.model.daml.finance.interface$.settlement.types.Allocation"));
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Allocation> valueDecoder() {
    return value$ -> {
      com.daml.ledger.javaapi.data.Variant variant$ = value$.asVariant().orElseThrow(() -> new IllegalArgumentException("Expected Variant to build an instance of the Variant com.daml.quickstart.model.daml.finance.interface$.settlement.types.Allocation"));
      if ("Unallocated".equals(variant$.getConstructor())) {
        return valueDecoderUnallocated().decode(variant$);
      }
      if ("Pledge".equals(variant$.getConstructor())) {
        return valueDecoderPledge().decode(variant$);
      }
      if ("CreditReceiver".equals(variant$.getConstructor())) {
        return valueDecoderCreditReceiver().decode(variant$);
      }
      if ("SettleOffledger".equals(variant$.getConstructor())) {
        return valueDecoderSettleOffledger().decode(variant$);
      }
      if ("PassThroughFrom".equals(variant$.getConstructor())) {
        return valueDecoderPassThroughFrom().decode(variant$);
      }
      throw new IllegalArgumentException("Found unknown constructor variant$.getConstructor() for variant com.daml.quickstart.model.daml.finance.interface$.settlement.types.Allocation, expected one of [Unallocated, Pledge, CreditReceiver, SettleOffledger, PassThroughFrom]");
    } ;
  }

  private static ValueDecoder<Unallocated> valueDecoderUnallocated() throws
      IllegalArgumentException {
    return value$ -> {
      Value variantValue$ = PrimitiveValueDecoders.variantCheck("Unallocated", value$);
      Unit body = PrimitiveValueDecoders.fromUnit.decode(variantValue$);
      return new Unallocated(body);
    } ;
  }

  private static ValueDecoder<Pledge> valueDecoderPledge() throws IllegalArgumentException {
    return value$ -> {
      Value variantValue$ = PrimitiveValueDecoders.variantCheck("Pledge", value$);
      Base.ContractId body =
          new Base.ContractId(variantValue$.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected body to be of type com.daml.ledger.javaapi.data.ContractId")).getValue());
      return new Pledge(body);
    } ;
  }

  private static ValueDecoder<CreditReceiver> valueDecoderCreditReceiver() throws
      IllegalArgumentException {
    return value$ -> {
      Value variantValue$ = PrimitiveValueDecoders.variantCheck("CreditReceiver", value$);
      Unit body = PrimitiveValueDecoders.fromUnit.decode(variantValue$);
      return new CreditReceiver(body);
    } ;
  }

  private static ValueDecoder<SettleOffledger> valueDecoderSettleOffledger() throws
      IllegalArgumentException {
    return value$ -> {
      Value variantValue$ = PrimitiveValueDecoders.variantCheck("SettleOffledger", value$);
      Unit body = PrimitiveValueDecoders.fromUnit.decode(variantValue$);
      return new SettleOffledger(body);
    } ;
  }

  private static ValueDecoder<PassThroughFrom> valueDecoderPassThroughFrom() throws
      IllegalArgumentException {
    return value$ -> {
      Value variantValue$ = PrimitiveValueDecoders.variantCheck("PassThroughFrom", value$);
      Tuple2<AccountKey, InstructionKey> body =
          Tuple2.<AccountKey,
          InstructionKey>valueDecoder(AccountKey.valueDecoder(),
          InstructionKey.valueDecoder()).decode(variantValue$);
      return new PassThroughFrom(body);
    } ;
  }
}
