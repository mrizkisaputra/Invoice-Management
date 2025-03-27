# Invoice Management

Aplikasi ini dipakai untuk mengelola invoice dan menyambungkan dengan berbagai metode. Diantara metode pembayaran yang akan di support antara lain adalah:

* Virtual Account Bank
    * Bank BNI
    * Bank CIMB
    * Bank BSI
* e-Wallet
    * Ovo
    * Gopay
* QR Payment
    * QRIS

Tipe tagihan yang tersedia:

  * CLOSED : bayar sesuai nominal, jika tidak sesuai, ditolak
  * OPEN : pembayaran berapapun diterima
  * INSTALLMENT : pembayaran diterima selama total akumulasi lebih kecil atau sama dengan nilai tagihan

Fitur Aplikasi:

* Manajemen Debitur
  * registrasi debitur
  * rekap tagihan debitur
  * histori pembayaran

* Manajemen Invoice
  * membuat invoice
  * mengganti nilai dan tanggal jatuh tempo
  * membatalkan invoice

## Tech Stack
Daftar framework & library apa saja yang digunakan:
* Java (programming language)
* Postgresql (relational database)
* Spring Data JPA (object relational mapping)
* Thymeleaf (modern server side java template engine)
* Spring Web (java web framework)
* Flyway Migration (database migration)
* Postgresql Driver (java program to connect to a postgresql database)
* Lombok (java annotation library for reduce boilerplate code)
* Spring Validation

## Schema Database Relational

![schema db](./skema_invoicedb.png)
![schema db](./security_schema_db.png)


## Tech Stack

1. Spring Framework
   - Spring Boot Starter Web
   - Spring Boot Starter Jpa
   - Spring Boot Starter Validation
   - Spring Boot Starter Thymeleaf
   - Spring Boot Starter Security

2. Database
   - PostgreSQL

3. Migration Tools
   - Flyway

4. Template Engine
   - Thymeleaf Extras Spring Security
   - Thymeleaf Layout Dialect
   - Thymeleaf Spring Data Dialect

5. Utilities
   - Lombok