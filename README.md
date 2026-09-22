# Relay

### Event-Driven E-Commerce Microservices Platform

Relay is a containerized, event-driven e-commerce platform built with **Java 17** and **Spring Boot**, designed to demonstrate production-grade microservices architecture rather than a simple CRUD API.

The system models a realistic order-to-payment lifecycle across independent services — Product, Order, Payment, Inventory, Notification, and Audit — communicating asynchronously through **Amazon SQS** and **SNS**. It implements the patterns that matter in real distributed systems: idempotent event consumption, retries with dead-letter queues, event fan-out, and service-level observability.

Locally, AWS services are emulated with **Floci**; in production, the platform deploys to **ECS** behind an **API Gateway**, with images built and pushed via **GitHub Actions** CI/CD.

**Key architectural themes**
- Asynchronous, event-driven communication (SQS → SNS fan-out)
- Idempotent message processing and duplicate-event protection
- Retry logic with dead-letter queue handling for failed payments
- Independent microservice data ownership
- Full observability via Spring Actuator and CloudWatch
- Containerized deployment pipeline (Docker → ECR → ECS)

---

## Architecture

```
                         ┌──────────────┐
                         │    Client    │
                         └──────┬───────┘
                                │
                                ▼
                         ┌─────────────────┐
                         │   API Gateway   │
                         └────────┬────────┘
                                  │
              ┌──────────────────┼──────────────────┐
              │                  │                   │
              ▼                  ▼                   ▼
       ┌─────────────┐   ┌─────────────┐     ┌─────────────┐
       │   Product   │   │    Order    │     │   Payment   │
       │   Service   │   │   Service   │     │   Service   │
       └──────┬──────┘   └──────┬──────┘     └──────┬──────┘
              │                 │                    │
              ▼                 ▼                    ▼
          Database          Database             Database
                                │
                                ▼
                         ┌─────────────┐
                         │     SQS     │
                         │ Order Queue │
                         └──────┬──────┘
                                │
                                ▼
                          Payment Worker
                                │
                                ▼
                         ┌─────────────┐
                         │     SNS     │
                         │Order Events │
                         └──────┬──────┘
                                │
                ┌───────────────┼───────────────┐
                ▼                ▼                ▼
          Notification      Inventory          Audit
            Service          Service           Service
```

**Business flow (happy path):**

```
Create product → Add inventory → Customer creates order → Order saved as PENDING
  → ORDER_CREATED published → SQS → Payment Service consumes event
  → Payment processed → PAYMENT_SUCCESSFUL published → SNS fan-out
  → Inventory reserves stock, Notification created, Audit event recorded
  → Order becomes PAID/PROCESSING → Order eventually COMPLETED
```

**Failure handling:**

```
Order → Payment → FAIL → Retry → Retry → Retry → DLQ (payment-dlq)
```

**Duplicate event protection:**

```
ORDER_CREATED → Message 1 → Process
             └→ Message 2 → Detect duplicate → Ignore
```

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot, Spring Web, Spring Data/JDBC, Bean Validation |
| Database | PostgreSQL |
| Messaging | Amazon SQS, Amazon SNS |
| Storage | Amazon S3 (product images) |
| Gateway | Amazon API Gateway |
| Containers | Docker, Amazon ECR, Amazon ECS |
| Observability | Spring Actuator, Amazon CloudWatch |
| Secrets/Access | AWS IAM, Secrets Manager |
| Local AWS emulation | Floci |
| CI/CD | GitHub Actions |
| Testing | JUnit 5, Mockito |
| API Docs | OpenAPI / Swagger |

---

## Services

| Service | Responsibility |
|---|---|
| **API Gateway** | External entry point, routes clients to internal services |
| **Product Service** | Product/catalog management, pricing, availability |
| **Order Service** | Order lifecycle, item pricing, order totals |
| **Payment Service** | Simulated payment processing, retries, idempotency |
| **Inventory Service** | Stock levels, reservation, oversell prevention |
| **Notification Service** | Customer-facing order/payment notifications |
| **Audit Service** | Append-only record of all significant system events |

---

## Getting Started

### Prerequisites
- Java 17
- Maven
- Docker Desktop
- Floci (local AWS emulation)
- PostgreSQL

### Run locally

```bash
git clone https://github.com/<your-username>/relay.git
cd relay
docker-compose up -d        # starts PostgreSQL + Floci
./mvnw clean install
./mvnw -pl product-service spring-boot:run
```

Each service exposes a health check once running:

```
GET /actuator/health
```

### API Documentation

Once a service is running, Swagger UI is available at:

```
http://localhost:<port>/swagger-ui.html
```

---

## Example Endpoints

**Products**
```
POST   /products
GET    /products
GET    /products/{id}
PUT    /products/{id}
DELETE /products/{id}
```

**Orders**
```
POST /orders
GET  /orders/{id}
GET  /orders/customer/{customerId}
POST /orders/{id}/cancel
```

**Payments**
```
GET /payments/{id}
GET /payments/order/{orderId}
```

---

## Testing Strategy

- **Unit tests** — business logic, validation, calculations, status transitions
- **Integration tests** — Controller → Service → Database
- **Messaging tests** — Producer → SQS → Consumer, Producer → SNS → Subscribers
- **Failure tests** — duplicate events, payment failure, database failure, malformed messages, consumer failure, insufficient stock

---

## Project Structure

```
relay/
├── product-service/
├── order-service/
├── payment-service/
├── inventory-service/
├── notification-service/
├── audit-service/
├── api-gateway/
├── infrastructure/
│   ├── floci/
│   ├── docker/
│   └── aws/
├── docs/
│   ├── architecture/
│   ├── api/
│   └── decisions/
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

## Roadmap

- [x] Product Service (CRUD, validation, pagination)
- [x] Order Service (order lifecycle, pricing)
- [x] Asynchronous payment processing via SQS
- [x] Idempotent event consumption
- [x] Retry + dead-letter queue handling
- [x] SNS event fan-out (Inventory, Notification, Audit)
- [ ] S3 product image storage
- [ ] API Gateway integration
- [ ] Observability (Actuator + CloudWatch)
- [ ] Full Docker containerization
- [ ] CI/CD pipeline (GitHub Actions → ECR → ECS)

---

## License

MIT
