package com.ms.payments.kafka.producer;

public class MXMessage {

    public static final String PACS008_TEMPLATE = """
            <?xml version="1.0" encoding="UTF-8"?>
            <Document xmlns="urn:iso:std:iso:20022:tech:xsd:pacs.008.001.04" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="urn:iso:std:iso:20022:tech:xsd:pacs.008.001.04 file://BE-FILE01/tverschu$/SR%20(Standards%20Release)/SR2013/ISO/Payments%20Clearing%20and%20settlement/SR2013_MX_Schemas_PaymentsClearingAndSettlement/pacs.008.001.04.xsd">
            	<FIToFICstmrCdtTrf>
            		<GrpHdr>
            			<MsgId>_MESSAGE_KEY_</MsgId>
            			<CreDtTm>2012-11-09T10:13:00</CreDtTm>
            			<NbOfTxs>1</NbOfTxs>
            			<SttlmInf>
            				<SttlmMtd>CLRG</SttlmMtd>
            				<ClrSys>
            					<Prtry>CBJ</Prtry>
            				</ClrSys>
            			</SttlmInf>
            			<InstgAgt>
            				<FinInstnId>
            					<BICFI>BBBBIE2D</BICFI>
            				</FinInstnId>
            			</InstgAgt>
            			<InstdAgt>
            				<FinInstnId>
            					<BICFI>CCCCIE2D</BICFI>
            				</FinInstnId>
            			</InstdAgt>
            		</GrpHdr>
            		<CdtTrfTxInf>
            			<PmtId>
            				<InstrId>BBBB/121109/-CBJ056/1</InstrId>
            				<EndToEndId>CROPS/SX-25T/2012-10-13</EndToEndId>
            				<TxId>AAAA/121109-CCT/EUR/443/1</TxId>
            			</PmtId>
            			<IntrBkSttlmAmt Ccy="EUR">74900</IntrBkSttlmAmt>
            			<IntrBkSttlmDt>2012-11-09</IntrBkSttlmDt>
            			<InstdAmt Ccy="EUR">75000</InstdAmt>
            			<ChrgBr>SHAR</ChrgBr>
            			<ChrgsInf>
            				<Amt Ccy="EUR">100</Amt>
            				<Agt>
            					<FinInstnId>
            						<BICFI>BBBBIE2D</BICFI>
            					</FinInstnId>
            				</Agt>
            			</ChrgsInf>
            			<UltmtDbtr>
            				<Nm>Biogenetics - CROPS</Nm>
            				<PstlAdr>
            					<StrtNm>Growth Street</StrtNm>
            					<BldgNb>17</BldgNb>
            					<PstCd>G5 OTW</PstCd>
            					<TwnNm>Glasgow</TwnNm>
            					<Ctry>GB</Ctry>
            				</PstlAdr>
            			</UltmtDbtr>
            			<Dbtr>
            				<Nm>Biogenetics - HQ</Nm>
            				<PstlAdr>
            					<StrtNm>Corn Street</StrtNm>
            					<BldgNb>13</BldgNb>
            					<PstCd>W6 8DR</PstCd>
            					<TwnNm>London</TwnNm>
            					<Ctry>GB</Ctry>
            				</PstlAdr>
            			</Dbtr>
            			<DbtrAcct>
            				<Id>
            					<Othr>
            						<Id>46373892034012</Id>
            					</Othr>
            				</Id>
            			</DbtrAcct>
            			<DbtrAgt>
            				<FinInstnId>
            					<BICFI>AAAAGB2L</BICFI>
            				</FinInstnId>
            			</DbtrAgt>
            			<CdtrAgt>
            				<FinInstnId>
            					<BICFI>CCCCIE2D</BICFI>
            				</FinInstnId>
            			</CdtrAgt>
            			<Cdtr>
            				<Nm>Seed Inc.</Nm>
            				<PstlAdr>
            					<StrtNm>Grain Lane</StrtNm>
            					<BldgNb>27</BldgNb>
            					<TwnNm>Dublin</TwnNm>
            					<Ctry>IE</Ctry>
            				</PstlAdr>
            			</Cdtr>
            			<CdtrAcct>
            				<Id>
            					<IBAN>IE29CCCC93115212345678</IBAN>
            				</Id>
            			</CdtrAcct>
            			<Purp>
            				<Cd>GDDS</Cd>
            			</Purp>
            			<RmtInf>
            				<Strd>
            					<RfrdDocInf>
            						<Tp>
            							<CdOrPrtry>
            								<Cd>CINV</Cd>
            							</CdOrPrtry>
            						</Tp>
            						<Nb>SX-25T</Nb>
            						<RltdDt>2012-10-13</RltdDt>
            					</RfrdDocInf>
            				</Strd>
            			</RmtInf>
            		</CdtTrfTxInf>
            	</FIToFICstmrCdtTrf>
            </Document>
            """;

}
