package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import java.util.List;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestInvestorDocs {
  private SDK sdk;
  private LegalNaturalPerson person;
  private Account account;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    person = AccountUtil.createLnp(sdk);
    account = AccountUtil.createAccount(sdk, person);
  }

  @Order(1)
  @Test
  public void investor_docs_investor_docs_batch_create_upload_links_batch_create_upload_links1()
      throws Exception {
    var request =
        BatchCreateUploadLinksRequestCreate.builder()
            .createDocumentUploadLinkRequest(
                List.of(
                    CreateUploadLinkRequestCreate.builder()
                        .accountDocumentUploadRequest(
                            AccountDocumentUploadRequestCreate.builder()
                                .correspondentId(SdkUtil.getCorrespondentId())
                                .documentType(DocumentType.FDIC_SWEEP_PROGRAM_AGREEMENT)
                                .accountId(account.accountId().get())
                                .build())
                        .mimeType("image/jpeg")
                        .clientBatchSourceId("cda89bd0-a6bc-4acc-89da-d35bde30cbf4")
                        .build()))
            .build();

    var response = sdk.investorDocs().batchCreateUploadLinks(request);
    Assertions.assertEquals(200, response.statusCode());
  }

  @Order(2)
  @Test
  public void investor_docs_investor_docs_list_documents_list_documents1() throws Exception {
    var result =
        sdk.investorDocs()
            .listDocuments()
            .pageSize(50)
            .filter(String.format("correspondent_id==\"%s\"", SdkUtil.getCorrespondentId()))
            .call();
    Assertions.assertEquals(200, result.statusCode());
  }
}
