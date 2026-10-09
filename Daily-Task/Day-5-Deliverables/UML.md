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

## Branching Demonstration

This version was created on the `day-5-v2-branching-conflict` feature branch
and merged into `main` after resolving a deliberate documentation conflict.
```
