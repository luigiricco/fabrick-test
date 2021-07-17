# fabrick-test   
GET http://localhost:9090/fabrick/transactions/14537780?fromAccountingDate=2019-01-01&toAccountingDate=2019-12-31   
GET http://localhost:9090/fabrick/balance/14537780   
POST http://localhost:9090/fabrick/transfer/   
   
{   
   "accountId" : 123456,   
   "receiverName": "PIPPO",   
   "description": "des",   
   "currency": "EUR",   
   "amount": "123",   
   "executionDate" : "2021-07-21"   
   
}   
