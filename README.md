# 👨‍🌾 JikFarm (농산물 직거래 콘솔 애플리케이션)

<br>

## 📋 목차

1. [프로젝트 소개](#-프로젝트-소개)
2. [프로젝트 기획](#-프로젝트-기획)
   - [주제 선정 이유](#1-주제-선정-이유)
   - [프로젝트 차별점 및 특징](#2-프로젝트-차별점-및-특징)
3. [개발 기간](#-개발-기간)
4. [개발자 정보](#-개발자-정보)
5. [개발 환경 및 사용 기술](#️-개발-환경-및-사용-기술)
6. [주요 기능](#-주요-기능)
7. [다이어그램](#-다이어그램)
   - [유스케이스 다이어그램](#1-유스케이스-다이어그램-use-case-diagram)
   - [전체 클래스 다이어그램](#2-전체-클래스-다이어그램-overall-class-diagram)
   - [액티비티 다이어그램](#3-액티비티-다이어그램-상품-주문-activity-diagram)
8. [작동 시나리오](#️-작동-시나리오)


<br>

## 📖 프로젝트 소개

JikFarm은 텍스트 기반의 콘솔 환경에서 동작하는 온라인 농산물 직거래 장터 애플리케이션입니다. 사용자는 관리자와 일반 회원으로 나뉘며, 상품을 등록하고 검색, 주문, 관리하는 등 온라인 쇼핑몰의 핵심 기능을 통해 객체 지향 프로그래밍과 계층형 아키텍처 설계를 학습하는 것을 목표로 합니다.

<br>

## 💡 프로젝트 기획

### 1. 프로젝트 목적

* **객체 지향 프로그래밍(OOP) 심화 학습:** `User`, `Product`, `Order` 등 현실 세계의 개념을 객체로 모델링하고, 클래스 간의 관계(상속, 포함, 의존 등)를 설계하며 OOP의 주요 원칙을 깊이 있게 체득할 수 있습니다.
* **계층형 아키텍처(Layered Architecture) 설계 연습:** 사용자의 입출력을 담당하는 **Presentation Layer**(App), 비즈니스 로직을 처리하는 **Service Layer**, 데이터 영속성을 관리하는 **Data Access Layer**(DAO)로 역할을 분리하여, 유지보수성과 확장성이 뛰어난 코드 구조를 설계하는 경험을 할 수 있습니다.
* **CRUD 기능의 종합적 구현:** 모든 서비스의 기본이 되는 데이터 처리 기능, 즉 생성(Create), 조회(Read), 수정(Update), 삭제(Delete)를 각 기능별로 모두 구현해보며 데이터 관리의 전체 흐름을 익힐 수 있습니다.

### 2. 프로젝트 차별점 및 특징

단순한 기능 구현을 넘어, 현업에서 사용되는 설계 패턴과 원칙을 적용하여 코드의 품질을 높이고자 노력했습니다.

* **인터페이스 기반의 느슨한 결합(Loose Coupling) 설계:**
    `Service`와 `DAO` 계층을 모두 인터페이스로 추상화하고, 실제 구현 클래스는 인터페이스에 의존하도록 설계했습니다. 이를 통해 나중에 데이터 저장 방식을 파일이 아닌 실제 데이터베이스로 변경하더라도 `Service` 계층의 코드 수정 없이 유연하게 확장할 수 있습니다.

* **기능 단위 패키지 구조(Package-by-Feature):**
    `product`, `user`, `order` 등 기능(도메인) 단위로 패키지를 구성하여 관련된 코드(VO, DAO, Service 등)의 응집도를 높였습니다. 이는 역할별(Layer)로 패키지를 구성하는 방식보다 특정 기능을 수정하거나 파악할 때 더 편리합니다.

* **상속을 통한 코드 재사용성 향상:**
    메모리 기반의 `HashMapDAO`가 가진 기본 데이터 관리 로직을 파일 저장 기능을 가진 `ObjFileHashMapDAO`가 **상속(extends)**받아 재사용합니다. 이를 통해 중복 코드를 제거하고, 파일 입출력이라는 추가 기능에만 집중하여 개발 효율성을 높였습니다.

* **체계적인 설계 및 문서화:**
    요구사항 정의부터 유스케이스, 클래스, 액티비티 다이어그램 작성에 이르기까지, UML을 활용한 체계적인 설계 및 문서화 과정을 통해 프로젝트의 논리적 구조를 시각화하고 안정성을 검증하고자 노력했습니다.

<br>

## 🛠️ 개발 환경 및 사용 기술

* **언어:** Java (JDK 11)
* **IDE:** IntelliJ IDEA (or Eclipse)
* **데이터 저장:** Java 객체 직렬화(Serialization)를 이용한 파일 시스템
* **설계 및 문서화:** PlantUML, Markdown

<br>

## 📌 주요 기능

* **회원 관리:** 회원가입, 로그인/로그아웃, 내 정보 관리, 회원 탈퇴
* **상품 관리:** 상품 목록 조회, 이름 검색, 가격/이름순 정렬
* **주문 관리:** 상품 즉시 주문, 장바구니 일괄 주문, 주문 내역 조회
* **장바구니:** 장바구니 상품 추가, 조회, 삭제, 비우기
* **관리자 기능:** 전체 회원/주문 목록 조회, 상품 등록/수정/삭제

<br>

## 📊 다이어그램

### 1. 유스케이스 다이어그램 (Use Case Diagram)

* 시스템의 전체적인 기능과 사용자의 상호작용을 나타냅니다.

    ![유스케이스 다이어그램 이미지](이미지_경로/use_case_diagram.png)
    *위 이미지는 아래 PlantUML 코드로 생성되었습니다.*
    <details>
    <summary>PlantUML 코드 보기</summary>

    ```plantuml
    @startuml
    left to right direction
    title JikFarm 시스템 유스케이스 다이어그램

    actor "방문자" as visitor
    actor "회원" as member
    actor "관리자" as admin

    rectangle "JikFarm 시스템" {
      usecase "회원가입" as uc_signup
      usecase "로그인" as uc_login
      usecase "로그아웃" as uc_logout
      usecase "상품 목록 조회" as uc_view_products
      usecase "상품 검색" as uc_search
      usecase "상품 정렬" as uc_sort
      usecase "상품 주문" as uc_order
      usecase "장바구니 관리" as uc_manage_cart
      usecase "내 주문내역 확인" as uc_view_my_orders
      usecase "내 정보 관리" as uc_manage_my_info
      usecase "상품 등록" as uc_add_product
      usecase "상품 정보 수정" as uc_edit_product
      usecase "상품 삭제" as uc_delete_product
      usecase "전체 회원 조회" as uc_view_all_users
      usecase "전체 주문 조회" as uc_view_all_orders
    }

    admin --|> member
    member --|> visitor

    visitor -- uc_signup
    visitor -- uc_login
    visitor -- uc_view_products

    member -- uc_logout
    member -- uc_search
    member -- uc_sort
    member -- uc_order
    member -- uc_manage_cart
    member -- uc_view_my_orders
    member -- uc_manage_my_info

    admin -- uc_add_product
    admin -- uc_edit_product
    admin -- uc_delete_product
    admin -- uc_view_all_users
    admin -- uc_view_all_orders
    @enduml
    ```
    </details>

<br>

### 2. 전체 클래스 다이어그램 (Overall Class Diagram)

* 프로젝트의 실제 패키지 구조를 기반으로 클래스들의 관계를 시각화하여 전체 시스템의 아키텍처를 보여줍니다.

    ![클래스 다이어그램 이미지](이미지_경로/class_diagram.png)
    *위 이미지는 아래 PlantUML 코드로 생성되었습니다.*
    <details>
    <summary>PlantUML 코드 보기</summary>

    ```plantuml
    @startuml
    skinparam classAttributeIconSize 0
    skinparam linetype ortho
    hide empty members

    title JikFarm 전체 시스템 통합 클래스 다이어그램 (패키지 구조 반영)

    package "app" { class JikFarmConsoleApp }
    package "product" {
      interface ProductService
      class JFProductService
      interface ProductDAO
      class HashMapProductDAO
      class ObjFileHashMapProductDAO
      class ProductVO
    }
    package "user" {
      interface UserService
      class JFUserService
      interface UserDAO
      class HashMapUserDAO
      class ObjFileHashMapUserDAO
      class UserVO
    }
    package "order" {
      interface OrderService
      class OrderServiceImpl
      interface OrderDAO
      class ObjFileHashMapOrderDAO
      class OrderVO
      class OrderItemVO
    }
    package "cart" {
      interface CartService
      class CartServiceImpl
      interface CartDAO
      class HashMapCartDAO
      class CartItemVO
    }

    ' Application -> Service
    JikFarmConsoleApp --> product.ProductService
    JikFarmConsoleApp --> user.UserService
    JikFarmConsoleApp --> order.OrderService
    JikFarmConsoleApp --> cart.CartService

    ' Service <-> DAO/VO
    product.ProductService <|.. product.JFProductService
    product.JFProductService -- product.ProductDAO
    user.UserService <|.. user.JFUserService
    user.JFUserService -- user.UserDAO
    order.OrderService <|.. order.OrderServiceImpl
    order.OrderServiceImpl -- order.OrderDAO
    cart.CartService <|.. cart.CartServiceImpl
    cart.CartServiceImpl -- cart.CartDAO

    ' DAO 구현 및 상속
    product.ProductDAO <|.. product.HashMapProductDAO
    product.HashMapProductDAO <|-- product.ObjFileHashMapProductDAO
    user.UserDAO <|.. user.HashMapUserDAO
    user.HashMapUserDAO <|-- user.ObjFileHashMapUserDAO
    order.OrderDAO <|.. order.ObjFileHashMapOrderDAO
    cart.DAO <|.. cart.HashMapCartDAO

    ' 패키지 간 협력
    order.OrderServiceImpl ..> product.ProductService : "uses"

    ' VO 관계
    order.OrderVO "1" o-- "*" order.OrderItemVO
    order.OrderItemVO -- "1" product.ProductVO
    cart.CartItemVO -- "1" product.ProductVO
    @enduml
    ```
    </details>

<br>

### 3. 액티비티 다이어그램: 상품 주문 (Activity Diagram)

* 사용자의 상품 주문 기능에 대한 내부 처리 흐름을 순서도 형식으로 나타냅니다.

    ![액티비티 다이어그램 이미지](이미지_경로/activity_diagram_order.png)
    *위 이미지는 아래 PlantUML 코드로 생성되었습니다.*
    <details>
    <summary>PlantUML 코드 보기</summary>

    ```plantuml
    @startuml
    title JikFarm '상품 주문' 프로세스 액티비티 다이어그램

    |사용자|
    start
    :메뉴에서 '상품 주문' 선택;
    |JikFarm 시스템|
    :주문 가능한 상품 목록 표시;
    |사용자|
    :주문할 상품 번호와 수량 입력;
    |JikFarm 시스템|
    :입력된 상품 정보 조회;
    if (상품이 존재하는가?) then ([Yes])
        if (재고가 충분한가?) then ([Yes])
            if (사용자의 배송 정보가 등록되어 있는가?) then ([No])
                |사용자|
                :배송 정보 입력;
                |JikFarm 시스템|
                :배송 정보를 회원 정보에 저장;
            endif
            |JikFarm 시스템|
            :주문 정보 생성 및 확인 요청;
            |사용자|
            :주문 내용 최종 확인;
            |JikFarm 시스템|
            :주문 정보 저장;
            :상품 재고 차감;
            :주문 완료 메시지 표시;
            stop
        else ([No])
            :재고 부족 메시지 표시;
            stop
        endif
    else ([No])
        :상품 없음 메시지 표시;
        stop
    endif
    @enduml
    ```
    </details>

<br>

## ⚙️ 작동 시나리오

신규 회원이 가입하여 상품을 주문하는 전체 과정에 대한 시나리오입니다.

| 단계 | 사용자 행동 (User Action) | 시스템 반응 (System Response) |
| :--- | :--- | :--- |
| **1** | `3. 회원 가입` 메뉴 선택 | 회원 가입 정보 입력 프롬프트 표시 |
| **2** | ID, PW, 이름 입력 | "회원 가입이 완료되었습니다." 메시지 표시 |
| **3** | `2. 로그인` 메뉴 선택 | ID, PW 입력 프롬프트 표시 |
| **4** | 가입한 ID, PW 입력 | 환영 메시지와 함께 회원 전용 메뉴 표시 |
| **5** | `1. 상품 목록` 선택 후 검색어로 '사과' 입력 | '꿀사과'가 포함된 목록 표시 |
| **6** | `4. 장바구니 상품 담기` 선택 후 상품번호 '111', 수량 '2' 입력 | "장바구니에 추가했습니다." 메시지 표시 |
| **7** | `5. 장바구니 보기` 선택 후 `1. 상품 주문` 선택 | 배송 정보 입력 프롬프트 표시 (첫 주문이므로) |
| **8** | 연락처, 주소 등 배송 정보 입력 | "주문이 완료되었습니다." 메시지 표시 |
| **9** | `0. 로그아웃` 선택 | 로그아웃 메시지 표시 후 초기 메뉴로 복귀 |
