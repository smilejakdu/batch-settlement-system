# batch-settlement-system

## Description

# claim table
```markdown
claim_receipt --> claim_item
claim_receipt --> claim_refund_history
claim_item --> order_item
```

# settlement table
```markdown
데일리 정상 pk
seller_id
날짜
주문번호
claim 번호
셀러 사업자 번호
세금 형태
판매 타입
pg 판매 금액
쿠폰 할인 금액
마일리지 사용 금액
발생 배송비 금액
발생 클레임 배송비 금액
수수료 금액
```
