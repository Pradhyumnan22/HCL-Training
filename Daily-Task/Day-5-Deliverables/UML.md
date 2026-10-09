# Day 5 UML Sketch

```text
                 <<abstract>>
                 BaseEntity
                    id
                     ▲
                     │
                 <<abstract>>
                    User
              name, getRole()
          ┌──────────┼──────────┐
        Admin       Agent     Traveller

                 <<abstract>>
                   Payment
                  amount, pay()
              ┌──────┼──────┐
        CardPayment UpiPayment CashPayment
             ◇             ◇
             └──── implements ────┘
                  Refundable

              RefundPolicy
       calculateRefund(amount, days)
                 ▲                 ▲
                 │                 │
       FullRefundPolicy   PartialRefundPolicy

## Merge Verification

The final UML documentation includes the resolved Day 5 merge result.
```
