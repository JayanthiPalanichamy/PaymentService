#Technologies in Scope
- Java
- Design Patterns
- Apache Camel (or other alternative open source integration framework)
- XML and JSON creation, validation and parsing
- REST APIs
- JMS API
- ActiveMQ (or similar open source JMS provider)
- (nice to have) OpenShift (or other alternative container/cloud platform)

PoC Description
The PoC is based on a solution that needs to perform fraud check before honoring the payment for a bank.
The systems that need to be mocked in the PoC are:
Payment Processing System (PPS)
Fraud Check System (FCS)
Broker System (BS) insulating the PPS and FCS
You need to develop 2 solutions:
for solution 1 all interfaces between the PPS and BS is based on messaging and JSON.
for solution 2 all interfaces between the PPS and BS is based on REST APIs and JSON.
for both solutions all interfaces between the BS and FCS is based on messaging and XML.
for both solutions the preferable communication between components inside each system should be messaging.
Payment Processing System (PPS)
receives a payment (in JSON) for processing.
performs basic validation on a payment (e.g. valid ISO country code, valid ISO currency code).
invokes a broker system in the BS for a fraud check on the payment.
processes the payment after the fraud check based on a approval or rejection response from the BS.
Broker System (BS)
receives a fraud check request for a payment (in JSON) from the PPS and converts to XML.
sends the payment (in XML) to the FCS for executing a fraud check.
receives the fraud check result (in XML) from the FCS.
converts the fraud check result to JSON and sends it to the PPS.
Fraud Check System (FCS)
receives a fraud check request for a payment (in XML) from the BS.
checks the payer and payee details (name, country, bank) and the payment instruction.
approves or rejects the payment based on the checks.
sends the results of the fraud check (e.g. approval, rejection) to the BS.
The system should perform the following checks based on blacklists:
Field Blacklist
payer and payee name “Mark Imaginary”, “Govind Real”, “Shakil Maybe”, “Chang Imagine”
payer and payee country CUB, IRQ, IRN, PRK, SDN, SYR
payer and payee bank “BANK OF KUNLUN”, “KARAMAY CITY COMMERCIAL BANK”
payment instruction “Artillery Procurement”, “Lethal Chemicals payment”
If any of the matches is found then reject the payment with message “Suspicious payment”, otherwise approve the payment with message “Nothing
found, all okay”.
Payment Payload
Field Name Field Category Field Size Field Value
Transaction ID Mandatory 36 Transaction ID in UUID format (https://en.wikipedia.org/wiki/Universally_unique_identifier)

Must be unique across the system and E2E traceable
Payer Name Mandatory 50 Payer's first and last name, for example: "Munster Muller"
Payer Bank Mandatory 50 Name of the payer's bank, for example: "Bank of America"
Payer Country Code Mandatory 3 ISO alpha-3 country code, for example: DEU, GBR,USA
Payee Name Mandatory 50 Payee's first and last name, for example: "Munster Muller"
Payee Bank Mandatory 50 Name of the payee's bank, for example: "BNP Paribas"
Payee Country Code Mandatory 3 ISO alpha-3 country code, for example: DEU, GBR, USA
Payment Instruction Optional 50 Free text, for example: "Loan Repayment", "Tax Reimbursements", etc
Execution Date Mandatory 10 ISO 8601 date format YYYY-MM-DD, for example: 2020-02-21
Amount Mandatory 18 2 decimal places must be supplied, for example: 17.45
Currency Mandatory 3 ISO 4217 currency code, for example: EUR, GBP
Creation Timestamp Mandatory 20 ISO 8601 UTC timestamp format YYYY-MM-DDThh:mm:ssZ, for example: 2004-02-21T17:00:00Z
Remarks
Define your own XML and JSON formats for the solutions.
Define your own payload for the fraud check result.
Use Apache AMQ (or any other open source) JMS provider. Please develop the solution using Java and Apache Camel (or other alternative open
source integration framework).
Deploy on (nice to have) OpenShift (or other alternative container/cloud platform) with tools of your choice that you want to showcase to log or
monitor the flows.
Define the REST APIs based on REST API best practices (e.g. CRUD).
Proper Audit logs need to be maintained in all components of the solutions.
Re-use as many as possible components and source code for the solution 1 and solution 2.
Structure of the Demo
Potential candidate should be able present the PoC (max 90 minutes).
The source code should also be shared in the demo. Questions regarding the source code will be raised.
A UML component diagram needs to be created and the solution should be explained with use of the diagram (the solution and design should be
well founded).
The solutions must be live executed and explained (no powerpoint demo).