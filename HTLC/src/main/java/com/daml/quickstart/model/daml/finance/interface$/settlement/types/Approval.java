package com.daml.quickstart.model.daml.finance.interface$.settlement.types;

import com.daml.ledger.javaapi.data.Unit;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.ledger.javaapi.data.codegen.Variant;
import com.daml.quickstart.model.da.types.Tuple2;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.approval.*;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.AccountKey;

public abstract class Approval extends Variant<Approval> {
  public static final String _packageId = "4b5bf7a6465e5e11800a03c131a5759565edd3ff9cfc43f41d1f7fbc72255587";

  public Approval() {
  }

  public abstract com.daml.ledger.javaapi.data.Variant toValue();

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Approval fromValue(Value value$) {
    com.daml.ledger.javaapi.data.Variant variant$ = value$.asVariant().orElseThrow(() -> new IllegalArgumentException("Expected Variant to build an instance of the Variant com.daml.quickstart.model.daml.finance.interface$.settlement.types.Approval"));
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Approval> valueDecoder() {
    return value$ -> {
      com.daml.ledger.javaapi.data.Variant variant$ = value$.asVariant().orElseThrow(() -> new IllegalArgumentException("Expected Variant to build an instance of the Variant com.daml.quickstart.model.daml.finance.interface$.settlement.types.Approval"));
      if ("Unapproved".equals(variant$.getConstructor())) {
        return valueDecoderUnapproved().decode(variant$);
      }
      if ("TakeDelivery".equals(variant$.getConstructor())) {
        return valueDecoderTakeDelivery().decode(variant$);
      }
      if ("DebitSender".equals(variant$.getConstructor())) {
        return valueDecoderDebitSender().decode(variant$);
      }
      if ("SettleOffledgerAcknowledge".equals(variant$.getConstructor())) {
        return valueDecoderSettleOffledgerAcknowledge().decode(variant$);
      }
      if ("PassThroughTo".equals(variant$.getConstructor())) {
        return valueDecoderPassThroughTo().decode(variant$);
      }
      throw new IllegalArgumentException("Found unknown constructor variant$.getConstructor() for variant com.daml.quickstart.model.daml.finance.interface$.settlement.types.Approval, expected one of [Unapproved, TakeDelivery, DebitSender, SettleOffledgerAcknowledge, PassThroughTo]");
    } ;
  }

  private static ValueDecoder<Unapproved> valueDecoderUnapproved() throws IllegalArgumentException {
    return value$ -> {
      Value variantValue$ = PrimitiveValueDecoders.variantCheck("Unapproved", value$);
      Unit body = PrimitiveValueDecoders.fromUnit.decode(variantValue$);
      return new Unapproved(body);
    } ;
  }

  private static ValueDecoder<TakeDelivery> valueDecoderTakeDelivery() throws
      IllegalArgumentException {
    return value$ -> {
      Value variantValue$ = PrimitiveValueDecoders.variantCheck("TakeDelivery", value$);
      AccountKey body = AccountKey.valueDecoder().decode(variantValue$);
      return new TakeDelivery(body);
    } ;
  }

  private static ValueDecoder<DebitSender> valueDecoderDebitSender() throws
      IllegalArgumentException {
    return value$ -> {
      Value variantValue$ = PrimitiveValueDecoders.variantCheck("DebitSender", value$);
      Unit body = PrimitiveValueDecoders.fromUnit.decode(variantValue$);
      return new DebitSender(body);
    } ;
  }

  private static ValueDecoder<SettleOffledgerAcknowledge> valueDecoderSettleOffledgerAcknowledge()
      throws IllegalArgumentException {
    return value$ -> {
      Value variantValue$ = PrimitiveValueDecoders.variantCheck("SettleOffledgerAcknowledge",
          value$);
      Unit body = PrimitiveValueDecoders.fromUnit.decode(variantValue$);
      return new SettleOffledgerAcknowledge(body);
    } ;
  }

  private static ValueDecoder<PassThroughTo> valueDecoderPassThroughTo() throws
      IllegalArgumentException {
    return value$ -> {
      Value variantValue$ = PrimitiveValueDecoders.variantCheck("PassThroughTo", value$);
      Tuple2<AccountKey, InstructionKey> body =
          Tuple2.<AccountKey,
          InstructionKey>valueDecoder(AccountKey.valueDecoder(),
          InstructionKey.valueDecoder()).decode(variantValue$);
      return new PassThroughTo(body);
    } ;
  }
}
