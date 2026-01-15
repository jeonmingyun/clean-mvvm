## 소개
현재 프로젝트는 클린 아키텍처의 기본 구조를 포함하고 있습니다.<br><br>

## 사용기술
**프레임워크 :** Android<br>
**아키텍처 :** MVVM, Clean Architecture <br>
**DB(로컬) :** Room<br>
**네트워킹(원격) :** Retrofit2<br>
**기 타 :** DataBinding, Dagger & Hilt, 코루틴, Flow, StateFlow, SharedFlow<br><br>

## 최종 앱 아키텍처 구조<br>
앱의 전체적인 아키텍처는 다음과 같이 3개의 계층으로 구성됩니다.<br>
- **Presentation Layer :** (UI) 화면 표시, 사용자 입력 처리 (Activity, Fragment, ViewModel, DataBinding)<br>
- **Domain Layer :** (Business Logic) 앱의 핵심 비즈니스 로직 (UseCase, Model)<br>
- **Data Layer :** (Data Sources) 데이터의 출처를 관리하고 제공 (Repository, Remote/Local DataSource, DTO, Entity)<br>

#### 📌 포인트<br>
`presantation`은 화면 단위<br>
`domain`은 순수 Kotlin<br>
`data`는 구현체<br><br>