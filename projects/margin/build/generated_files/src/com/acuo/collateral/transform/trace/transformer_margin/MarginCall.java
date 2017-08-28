package com.acuo.collateral.transform.trace.transformer_margin;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import com.tracegroup.transformer.plugin.*;
import com.tracegroup.transformer.configfiles.RuntimeContext;
import com.tracegroup.transformer.exposedservices.*;
import com.tracegroup.transformer.mom.MomNode;
import com.tracegroup.transformer.dad.MomNodeBasket;
import com.tracegroup.transformer.plugin.ClasspathProjectManager;
import com.tracegroup.transformer.tbeans.serviceperformers.*;
import com.tracegroup.transformer.transport.*;


/**
 * This class is auto-generated from ExposedService MarginCall.
 * <P/>
 * Created by lucie on 28 août 2017 18:05:50 using Project margin.tpj
 * <P/>
 * Services to transform from/to MarginSphere messages and Acuo data model
 * <P/>
 * Transformer is produced by Trace Financial Ltd.
 */

public class MarginCall implements Serializable {

  private static final IProjectProvider PROJECT_MANAGER
          = ClasspathProjectManager.getInstance(null,
                                                MarginCall.class.getClassLoader());

  private static final String PROJECT_KEY = "com.acuo.collateral.transform.trace.transformer-margin";

  public static final String SERVICE_NAME = "MarginCall";

  /**
   * Operation to agree a call
   */
  
  public AgreeCallOutputWrapper agreeCall(
            Object[] input)
          throws MomException, RuleException,
                 com.tracegroup.transformer.exposedservices.UnrecognizedMessageException,
                 com.tracegroup.transformer.exposedservices.StructureException {

    ObjectBasket inputs = new ObjectBasket();
    inputs.add("Input", input);


    @SuppressWarnings("unchecked")
    StringBasket outBasket = ServicePerformerExecutor
            .performServiceWithSimpleExceptions(
                    PROJECT_MANAGER,
                    PROJECT_KEY,
                    SERVICE_NAME,
                    "agreeCall",
                    inputs,
                    StringBasket.class,
                    null);

    AgreeCallOutputWrapper wrapper = new AgreeCallOutputWrapper();

    String vMarginCalls
         = outBasket.get("marginCalls", String.class);
    wrapper.setMarginCalls(vMarginCalls);


    return wrapper;
  }

  /**
   * ExposedServiceOperation agreeCallResponse
   */
  
  public AgreeCallResponseOutputWrapper agreeCallResponse(
            String input)
          throws MomException, RuleException,
                 com.tracegroup.transformer.exposedservices.UnrecognizedMessageException,
                 com.tracegroup.transformer.exposedservices.StructureException {

    StringBasket inputs = new StringBasket();
    inputs.add("Input", input);


    @SuppressWarnings("unchecked")
    ObjectBasket outBasket = ServicePerformerExecutor
            .performServiceWithSimpleExceptions(
                    PROJECT_MANAGER,
                    PROJECT_KEY,
                    SERVICE_NAME,
                    "agreeCallResponse",
                    inputs,
                    ObjectBasket.class,
                    null);

    AgreeCallResponseOutputWrapper wrapper = new AgreeCallResponseOutputWrapper();

    Object[] aOutput
         = outBasket.get("Output", Object[].class);
    wrapper.setOutput(aOutput);
    Object[] aMSError
         = outBasket.get("MSError", Object[].class);
    wrapper.setMSError(aMSError);

    return wrapper;
  }

  /**
   * ExposedServiceOperation CallDeliveryMapUpdate
   */
  
  public CallDeliveryMapUpdateOutputWrapper callDeliveryMapUpdate(
            Object[] input)
          throws MomException, RuleException,
                 com.tracegroup.transformer.exposedservices.UnrecognizedMessageException,
                 com.tracegroup.transformer.exposedservices.StructureException {

    ObjectBasket inputs = new ObjectBasket();
    inputs.add("Input", input);


    @SuppressWarnings("unchecked")
    StringBasket outBasket = ServicePerformerExecutor
            .performServiceWithSimpleExceptions(
                    PROJECT_MANAGER,
                    PROJECT_KEY,
                    SERVICE_NAME,
                    "CallDeliveryMapUpdate",
                    inputs,
                    StringBasket.class,
                    null);

    CallDeliveryMapUpdateOutputWrapper wrapper = new CallDeliveryMapUpdateOutputWrapper();

    String vOutput
         = outBasket.get("Output", String.class);
    wrapper.setOutput(vOutput);


    return wrapper;
  }

  /**
   * ExposedServiceOperation cancelCall
   */
  
  public CancelCallOutputWrapper cancelCall(
            Object[] input)
          throws MomException, RuleException,
                 com.tracegroup.transformer.exposedservices.UnrecognizedMessageException,
                 com.tracegroup.transformer.exposedservices.StructureException {

    ObjectBasket inputs = new ObjectBasket();
    inputs.add("Input", input);


    @SuppressWarnings("unchecked")
    StringBasket outBasket = ServicePerformerExecutor
            .performServiceWithSimpleExceptions(
                    PROJECT_MANAGER,
                    PROJECT_KEY,
                    SERVICE_NAME,
                    "cancelCall",
                    inputs,
                    StringBasket.class,
                    null);

    CancelCallOutputWrapper wrapper = new CancelCallOutputWrapper();

    String vOutput
         = outBasket.get("Output", String.class);
    wrapper.setOutput(vOutput);


    return wrapper;
  }

  /**
   * ExposedServiceOperation cancelCallResponse
   */
  
  public CancelCallResponseOutputWrapper cancelCallResponse(
            String input)
          throws MomException, RuleException,
                 com.tracegroup.transformer.exposedservices.UnrecognizedMessageException,
                 com.tracegroup.transformer.exposedservices.StructureException {

    StringBasket inputs = new StringBasket();
    inputs.add("Input", input);


    @SuppressWarnings("unchecked")
    ObjectBasket outBasket = ServicePerformerExecutor
            .performServiceWithSimpleExceptions(
                    PROJECT_MANAGER,
                    PROJECT_KEY,
                    SERVICE_NAME,
                    "cancelCallResponse",
                    inputs,
                    ObjectBasket.class,
                    null);

    CancelCallResponseOutputWrapper wrapper = new CancelCallResponseOutputWrapper();

    Object[] aOutput
         = outBasket.get("Output", Object[].class);
    wrapper.setOutput(aOutput);
    Object[] aMSError
         = outBasket.get("MSError", Object[].class);
    wrapper.setMSError(aMSError);

    return wrapper;
  }

  /**
   * ExposedServiceOperation createCall
   */
  
  public CreateCallOutputWrapper createCall(
            Object[] input)
          throws MomException, RuleException,
                 com.tracegroup.transformer.exposedservices.UnrecognizedMessageException,
                 com.tracegroup.transformer.exposedservices.StructureException {

    ObjectBasket inputs = new ObjectBasket();
    inputs.add("Input", input);


    @SuppressWarnings("unchecked")
    StringBasket outBasket = ServicePerformerExecutor
            .performServiceWithSimpleExceptions(
                    PROJECT_MANAGER,
                    PROJECT_KEY,
                    SERVICE_NAME,
                    "createCall",
                    inputs,
                    StringBasket.class,
                    null);

    CreateCallOutputWrapper wrapper = new CreateCallOutputWrapper();

    String vOutput
         = outBasket.get("Output", String.class);
    wrapper.setOutput(vOutput);


    return wrapper;
  }

  /**
   * ExposedServiceOperation createCallReponse
   */
  
  public CreateCallReponseOutputWrapper createCallReponse(
            String input)
          throws MomException, RuleException,
                 com.tracegroup.transformer.exposedservices.UnrecognizedMessageException,
                 com.tracegroup.transformer.exposedservices.StructureException {

    StringBasket inputs = new StringBasket();
    inputs.add("Input", input);


    @SuppressWarnings("unchecked")
    ObjectBasket outBasket = ServicePerformerExecutor
            .performServiceWithSimpleExceptions(
                    PROJECT_MANAGER,
                    PROJECT_KEY,
                    SERVICE_NAME,
                    "createCallReponse",
                    inputs,
                    ObjectBasket.class,
                    null);

    CreateCallReponseOutputWrapper wrapper = new CreateCallReponseOutputWrapper();

    Object[] aOutput
         = outBasket.get("Output", Object[].class);
    wrapper.setOutput(aOutput);
    Object[] aMSError
         = outBasket.get("MSError", Object[].class);
    wrapper.setMSError(aMSError);

    return wrapper;
  }

  /**
   * ExposedServiceOperation disputeCall
   */
  
  public DisputeCallOutputWrapper disputeCall(
            Object[] input)
          throws MomException, RuleException,
                 com.tracegroup.transformer.exposedservices.UnrecognizedMessageException,
                 com.tracegroup.transformer.exposedservices.StructureException {

    ObjectBasket inputs = new ObjectBasket();
    inputs.add("Input", input);


    @SuppressWarnings("unchecked")
    StringBasket outBasket = ServicePerformerExecutor
            .performServiceWithSimpleExceptions(
                    PROJECT_MANAGER,
                    PROJECT_KEY,
                    SERVICE_NAME,
                    "disputeCall",
                    inputs,
                    StringBasket.class,
                    null);

    DisputeCallOutputWrapper wrapper = new DisputeCallOutputWrapper();

    String vOutput
         = outBasket.get("Output", String.class);
    wrapper.setOutput(vOutput);


    return wrapper;
  }

  /**
   * ExposedServiceOperation disputeResponse
   */
  
  public DisputeResponseOutputWrapper disputeResponse(
            String input)
          throws MomException, RuleException,
                 com.tracegroup.transformer.exposedservices.UnrecognizedMessageException,
                 com.tracegroup.transformer.exposedservices.StructureException {

    StringBasket inputs = new StringBasket();
    inputs.add("Input", input);


    @SuppressWarnings("unchecked")
    ObjectBasket outBasket = ServicePerformerExecutor
            .performServiceWithSimpleExceptions(
                    PROJECT_MANAGER,
                    PROJECT_KEY,
                    SERVICE_NAME,
                    "disputeResponse",
                    inputs,
                    ObjectBasket.class,
                    null);

    DisputeResponseOutputWrapper wrapper = new DisputeResponseOutputWrapper();

    Object[] aOutput
         = outBasket.get("Output", Object[].class);
    wrapper.setOutput(aOutput);
    Object[] aMSError
         = outBasket.get("MSError", Object[].class);
    wrapper.setMSError(aMSError);

    return wrapper;
  }

  /**
   * ExposedServiceOperation fetchAgreementDetails
   */
  
  public FetchAgreementDetailsOutputWrapper fetchAgreementDetails(
            String input)
          throws MomException, RuleException,
                 com.tracegroup.transformer.exposedservices.UnrecognizedMessageException,
                 com.tracegroup.transformer.exposedservices.StructureException {

    StringBasket inputs = new StringBasket();
    inputs.add("Input", input);


    @SuppressWarnings("unchecked")
    ObjectBasket outBasket = ServicePerformerExecutor
            .performServiceWithSimpleExceptions(
                    PROJECT_MANAGER,
                    PROJECT_KEY,
                    SERVICE_NAME,
                    "fetchAgreementDetails",
                    inputs,
                    ObjectBasket.class,
                    null);

    FetchAgreementDetailsOutputWrapper wrapper = new FetchAgreementDetailsOutputWrapper();

    Object[] aOutput
         = outBasket.get("Output", Object[].class);
    wrapper.setOutput(aOutput);
    Object[] aMSError
         = outBasket.get("MSError", Object[].class);
    wrapper.setMSError(aMSError);

    return wrapper;
  }

  /**
   * ExposedServiceOperation fetchCalls
   */
  
  public FetchCallsOutputWrapper fetchCalls(
            String input)
          throws MomException, RuleException,
                 com.tracegroup.transformer.exposedservices.UnrecognizedMessageException,
                 com.tracegroup.transformer.exposedservices.StructureException {

    StringBasket inputs = new StringBasket();
    inputs.add("Input", input);


    @SuppressWarnings("unchecked")
    ObjectBasket outBasket = ServicePerformerExecutor
            .performServiceWithSimpleExceptions(
                    PROJECT_MANAGER,
                    PROJECT_KEY,
                    SERVICE_NAME,
                    "fetchCalls",
                    inputs,
                    ObjectBasket.class,
                    null);

    FetchCallsOutputWrapper wrapper = new FetchCallsOutputWrapper();

    Object[] aOutput
         = outBasket.get("Output", Object[].class);
    wrapper.setOutput(aOutput);

    return wrapper;
  }

  /**
   * ExposedServiceOperation importStatementItem
   */
  
  public ImportStatementItemOutputWrapper importStatementItem(
            String input)
          throws MomException, RuleException,
                 com.tracegroup.transformer.exposedservices.UnrecognizedMessageException,
                 com.tracegroup.transformer.exposedservices.StructureException {

    StringBasket inputs = new StringBasket();
    inputs.add("Input", input);


    @SuppressWarnings("unchecked")
    ObjectBasket outBasket = ServicePerformerExecutor
            .performServiceWithSimpleExceptions(
                    PROJECT_MANAGER,
                    PROJECT_KEY,
                    SERVICE_NAME,
                    "importStatementItem",
                    inputs,
                    ObjectBasket.class,
                    null);

    ImportStatementItemOutputWrapper wrapper = new ImportStatementItemOutputWrapper();

    Object[] aOutput
         = outBasket.get("Output", Object[].class);
    wrapper.setOutput(aOutput);

    return wrapper;
  }

  /**
   * ExposedServiceOperation pledgeAccept
   */
  
  public PledgeAcceptOutputWrapper pledgeAccept(
            Object[] input)
          throws MomException, RuleException,
                 com.tracegroup.transformer.exposedservices.UnrecognizedMessageException,
                 com.tracegroup.transformer.exposedservices.StructureException {

    ObjectBasket inputs = new ObjectBasket();
    inputs.add("Input", input);


    @SuppressWarnings("unchecked")
    StringBasket outBasket = ServicePerformerExecutor
            .performServiceWithSimpleExceptions(
                    PROJECT_MANAGER,
                    PROJECT_KEY,
                    SERVICE_NAME,
                    "pledgeAccept",
                    inputs,
                    StringBasket.class,
                    null);

    PledgeAcceptOutputWrapper wrapper = new PledgeAcceptOutputWrapper();

    String vOutput
         = outBasket.get("Output", String.class);
    wrapper.setOutput(vOutput);


    return wrapper;
  }

  /**
   * ExposedServiceOperation pledgeAcceptResponse
   */
  
  public PledgeAcceptResponseOutputWrapper pledgeAcceptResponse(
            String input)
          throws MomException, RuleException,
                 com.tracegroup.transformer.exposedservices.UnrecognizedMessageException,
                 com.tracegroup.transformer.exposedservices.StructureException {

    StringBasket inputs = new StringBasket();
    inputs.add("Input", input);


    @SuppressWarnings("unchecked")
    ObjectBasket outBasket = ServicePerformerExecutor
            .performServiceWithSimpleExceptions(
                    PROJECT_MANAGER,
                    PROJECT_KEY,
                    SERVICE_NAME,
                    "pledgeAcceptResponse",
                    inputs,
                    ObjectBasket.class,
                    null);

    PledgeAcceptResponseOutputWrapper wrapper = new PledgeAcceptResponseOutputWrapper();

    Object[] aOutput
         = outBasket.get("Output", Object[].class);
    wrapper.setOutput(aOutput);
    Object[] aMSError
         = outBasket.get("MSError", Object[].class);
    wrapper.setMSError(aMSError);

    return wrapper;
  }

  /**
   * ExposedServiceOperation pledgeCreate
   */
  
  public PledgeCreateOutputWrapper pledgeCreate(
            Object[] input)
          throws MomException, RuleException,
                 com.tracegroup.transformer.exposedservices.UnrecognizedMessageException,
                 com.tracegroup.transformer.exposedservices.StructureException {

    ObjectBasket inputs = new ObjectBasket();
    inputs.add("Input", input);


    @SuppressWarnings("unchecked")
    StringBasket outBasket = ServicePerformerExecutor
            .performServiceWithSimpleExceptions(
                    PROJECT_MANAGER,
                    PROJECT_KEY,
                    SERVICE_NAME,
                    "pledgeCreate",
                    inputs,
                    StringBasket.class,
                    null);

    PledgeCreateOutputWrapper wrapper = new PledgeCreateOutputWrapper();

    String vOutput
         = outBasket.get("Output", String.class);
    wrapper.setOutput(vOutput);


    return wrapper;
  }

  /**
   * ExposedServiceOperation pledgeCreateResponse
   */
  
  public PledgeCreateResponseOutputWrapper pledgeCreateResponse(
            String input)
          throws MomException, RuleException,
                 com.tracegroup.transformer.exposedservices.UnrecognizedMessageException,
                 com.tracegroup.transformer.exposedservices.StructureException {

    StringBasket inputs = new StringBasket();
    inputs.add("Input", input);


    @SuppressWarnings("unchecked")
    ObjectBasket outBasket = ServicePerformerExecutor
            .performServiceWithSimpleExceptions(
                    PROJECT_MANAGER,
                    PROJECT_KEY,
                    SERVICE_NAME,
                    "pledgeCreateResponse",
                    inputs,
                    ObjectBasket.class,
                    null);

    PledgeCreateResponseOutputWrapper wrapper = new PledgeCreateResponseOutputWrapper();

    Object[] aOutput
         = outBasket.get("Output", Object[].class);
    wrapper.setOutput(aOutput);
    Object[] aMSError
         = outBasket.get("MSError", Object[].class);
    wrapper.setMSError(aMSError);

    return wrapper;
  }




}
