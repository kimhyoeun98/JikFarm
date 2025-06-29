# 👨‍🌾 JikFarm (농산물 직거래 콘솔 애플리케이션)

<br>

## 📋 목차

1. [프로젝트 소개](#-프로젝트-소개)
2. [프로젝트 기획](#-프로젝트-기획)
   - [주제 선정 이유](#1-주제-선정-이유)
   - [프로젝트 차별점 및 특징](#2-프로젝트-차별점-및-특징)
3. [개발 환경 및 사용 기술](#️-개발-환경-및-사용-기술)
4. [주요 기능](#-주요-기능)
5. [다이어그램](#-다이어그램)
   - [유스케이스 다이어그램](#1-유스케이스-다이어그램-use-case-diagram)
   - [전체 클래스 다이어그램](#2-전체-클래스-다이어그램-overall-class-diagram)
   - [액티비티 다이어그램](#3-액티비티-다이어그램-상품-주문-activity-diagram)
6. [작동 시나리오](#️-작동-시나리오)


<br>

## 📖 프로젝트 소개

JikFarm은 텍스트 기반의 콘솔 환경에서 동작하는 온라인 농산물 직거래 장터 애플리케이션입니다. 관리자와 일반 회원으로 역할을 분리하고, 상품 등록, 검색, 주문, 관리 등 온라인 쇼핑몰의 핵심 기능을 구현하며 객체 지향 프로그래밍과 계층형 아키텍처 설계를 학습하는 것을 목표로 합니다.

<br>

## 💡 프로젝트 기획

### 🎯 주제 선정 이유

복잡한 유통 구조를 개선하는 **'농산물 직거래'**라는 현실적인 문제에 주목했습니다. 이 주제는 객체 지향 프로그래밍(OOP)과 계층형 아키텍처 등, 수업에서 배운 핵심적인 소프트웨어 공학 이론을 종합적으로 적용하고 심화 학습하기에 최적이라고 판단하여 프로젝트 주제로 선정했습니다.

단순한 기능 구현을 넘어, **배운 이론을 실제 코드에 녹여내고 설계 원칙을 체득하는 것**을 가장 큰 목표로 삼았습니다.

* **객체 지향 모델링 훈련:**
    생산자, 소비자, 상품, 주문 등 현실의 개념들을 `User`, `Product`, `Order`와 같은 **객체로 모델링**하고, 클래스 간의 관계를 설계하며 객체 지향 설계 원칙을 훈련했습니다.

* **계층형 아키텍처 적용:**
    사용자 인터페이스(App), 비즈니스 로직(Service), 데이터 관리(DAO)의 **역할을 명확히 분리**하는 3-Tier 아키텍처를 적용하여, 유지보수가 용이하고 유연한 코드 구조를 설계하는 방법을 학습했습니다.

* **설계 패턴의 실제적 구현:**
    특히 `Service`와 `DAO`를 **인터페이스 기반으로 설계**하고, **상속**을 통해 코드 재사용성을 높이는 등, 현업에서 중요하게 다루어지는 설계 패턴을 직접 구현해보는 데에 중점을 두었습니다.


### ✨ 프로젝트의 기술적 특징

* **인터페이스 기반의 유연한 설계:** Service와 DAO를 인터페이스로 추상화하여, 데이터 저장 방식을 파일에서 DB로 손쉽게 교체할 수 있는 '느슨한 결합(Loose Coupling)' 구조를 구현했습니다.
* **기능 중심의 패키지 구조:** 역할(Layer)이 아닌 기능(Feature: `product`, `user` 등) 단위로 패키지를 구성하여, 관련 코드의 응집도를 높이고 유지보수를 용이하게 만들었습니다.
* **상속을 통한 코드 재사용:** 메모리 기반 DAO(`HashMapDAO`)의 공통 로직을 파일 저장 DAO(`ObjFile...DAO`)가 상속받아, 중복 코드를 제거하고 핵심 기능에만 집중할 수 있도록 설계했습니다.
* **UML을 활용한 체계적인 문서화:** 요구사항부터 클래스, 액티비티 다이어그램까지 체계적인 설계를 통해 코드의 논리적 안정성을 확보하고, 프로젝트 전체 구조를 시각적으로 문서화했습니다.

<br>

## 🛠️ 개발 환경 및 사용 기술

* **언어:** Java (JDK 17)
* **IDE:** Eclipse
* **데이터 저장:** Java 객체 직렬화(Serialization)를 이용한 파일 시스템
* **설계 및 문서화:** Visual Paradigm, PlantUML, Markdown

<br>

## 패키지 구조 요약

```text
📁 **src (소스 루트)**
 │
 ├─ 📁 **app**
 │  ├─ 📜 **JikFarmConsoleApp.java** (애플리케이션 실행 및 메뉴 제어)
 │  └─ 📜 **MyAppReader.java** (사용자 입력 처리)
 │
 ├─ 📁 **cart**
 │  ├─ 📜 **CartDAO.java** (장바구니 DAO 인터페이스)
 │  ├─ 📜 **CartItemVO.java** (장바구니 항목 데이터)
 │  ├─ 📜 **CartService.java** (장바구니 서비스 인터페이스)
 │  ├─ 📜 **CartServiceImpl.java** (장바구니 서비스 구현체)
 │  └─ 📜 **HashMapCartDAO.java** (메모리 기반 DAO 구현체)
 │
 ├─ 📁 **order**
 │  ├─ 📜 **OrderDAO.java** (주문 DAO 인터페이스)
 │  ├─ 📜 **OrderItemVO.java** (주문 항목 데이터)
 │  ├─ 📜 **OrderService.java** (주문 서비스 인터페이스)
 │  ├─ 📜 **OrderServiceImpl.java** (주문 서비스 구현체)
 │  ├─ 📜 **OrderVO.java** (주문 데이터)
 │  └─ 📁 **file**
 │     └─ 📜 **ObjFileHashMapOrderDAO.java** (파일 저장 DAO 구현체)
 │
 ├─ 📁 **product**
 │  ├─ 📜 **ProductDAO.java** (상품 DAO 인터페이스)
 │  ├─ 📜 **ProductService.java** (상품 서비스 인터페이스)
 │  ├─ 📜 **ProductVO.java** (상품 데이터)
 │  ├─ 📜 **JFProductService.java** (상품 서비스 구현체)
 │  ├─ 📜 **HashMapProductDAO.java** (메모리 기반 DAO 구현체)
 │  ├─ 📜 **ListProductDAO.java** (리스트 기반 DAO 구현체 - *대체 가능*)
 │  └─ 📁 **file**
 │     ├─ 📜 **FileProductDB.java** (파일 DB 인터페이스)
 │     └─ 📜 **ObjFileHashMapProductDAO.java** (파일 저장 DAO 구현체)
 │
 └─ 📁 **user**
    ├─ 📜 **UserDAO.java** (사용자 DAO 인터페이스)
    ├─ 📜 **UserService.java** (사용자 서비스 인터페이스)
    ├─ 📜 **UserVO.java** (사용자 데이터)
    ├─ 📜 **JFUserService.java** (사용자 서비스 구현체)
    ├─ 📜 **HashMapUserDAO.java** (메모리 기반 DAO 구현체)
    └─ 📁 **file**
       ├─ 📜 **FileUserDB.java** (파일 DB 인터페이스)
       └─ 📜 **ObjFileHashMapUserDAO.java** (파일 저장 DAO 구현체)

### 📦 패키지별 역할 요약

| 패키지 | 주요 역할 | 설명 |
| :--- | :--- | :--- |
| **`app`** | **실행 및 제어** | 사용자와의 상호작용, 즉 메뉴 표시와 입력 처리를 담당하는 최상위 계층입니다. |
| **`cart`** | **장바구니 기능** | 로그인 세션 동안만 유지되는 임시 장바구니 기능을 관리합니다. |
| **`order`** | **주문 기능** | 주문 생성, 내역 조회 등 주문과 관련된 모든 비즈니스 로직과 데이터를 담당합니다. |
| **`product`** | **상품 기능** | 상품 정보, 재고, 검색 등 상품과 관련된 모든 기능을 담당합니다. |
| **`user`** | **사용자 기능** | 회원가입, 로그인 등 사용자 정보 및 인증과 관련된 모든 기능을 담당합니다. |
| **`*.file`** | **데이터 영속성** | 각 기능별 DAO를 상속받아, 객체 데이터를 파일에 저장하고 불러오는 역할을 합니다. |


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
