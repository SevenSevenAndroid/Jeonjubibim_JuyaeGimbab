


<h1 align="center">BE SOPT ANDROID</h1>
<p align="center">
<img src ="https://user-images.githubusercontent.com/58849278/118345049-fcf5e180-b56c-11eb-8059-0b8e759de5f4.png" width = 50%>
</p>
<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-1.4.31-yellowgreen?logo=kotlin"/>
  <img src="https://img.shields.io/badge/Android-4.1.2-blue?logo=Android+Studio"/>
  <img src="https://img.shields.io/badge/targetSdk-30-green?logo=Android"/>
  <img src="https://img.shields.io/badge/minSdk-21-green?logo=Android"/>
</p>

<h1 align="left">📑 Open source library</h1>

| 라이브러리                                                   | 목적                                                    |
| ------------------------------------------------------------ | ------------------------------------------------------- |
| [Retrofit2](https://github.com/square/retrofit)              |  Retrofit2 통신                                              |
| [Gson](https://github.com/google/gson)                       | 서버에서 받아온 Json 객체를 Gson으로 변환               |
| [OkHttp3](https://square.github.io/okhttp/)                   |  Util 기능 제작에 활용 |
| [Lottie](https://github.com/airbnb/lottie-android/)          | 로그인, 회원가입 페이지 Lottie Animation 사용 
| [Glide](https://github.com/bumptech/glide)                   | URL 형식의 이미지          |       
| [RxJava](https://github.com/ReactiveX/RxJava)                   | Observer Pattern 사용        |    
| [RxKotlin](https://github.com/ReactiveX/RxKotlin)                   | Observable Method 활용        |                  
| [LiveData](https://github.com/ravi8x/LiveData)                   | LifeCycleOwner 관찰자 등록        |    
| [Dagger2](https://github.com/google/dagger)                   | 의존성 주입        |  

 
<h1 align="left">📚 Assignments</h1>


📕[First Assignment](https://github.com/SevenSevenAndroid/Jeonjubibim_JuyaeGimbab/wiki/BE-SOPT-FIRST-ASSIGNMENT) 

📙[Second Assignment](https://github.com/SevenSevenAndroid/Jeonjubibim_JuyaeGimbab/wiki/BE-SOPT-SECOND-ASSIGNMENT) 




## 📘 Android Assignment Week 4

![image](https://user-images.githubusercontent.com/58849278/117603200-7a94a880-b18d-11eb-9d56-f4f3ce6c9391.png)

:heavy_check_mark:**Retrofit2 서버 통신 구현 순서**

![image](https://user-images.githubusercontent.com/58849278/117603887-14108a00-b18f-11eb-9555-645b8558e213.png)

## :pushpin: **Level 1 필수 과제**

(1) [SOPT signIn/signUp](https://www.notion.so/API-f960755d414d4c8181c2e0516c4a82a7)  로그인, 회원가입 통신

(2.1) signIn Postman
	
<image src="https://user-images.githubusercontent.com/58849278/118388604-7de1d580-b660-11eb-8238-72862b9ea899.png" width = 70%>
	
(2.2) signUp Postman

<image src="https://user-images.githubusercontent.com/58849278/118388629-98b44a00-b660-11eb-81a9-3056829d16ff.png" width = 70%>
	
(2.3) Retrofit2 interface 

<image src ="https://user-images.githubusercontent.com/58849278/117758273-b0549280-b25c-11eb-9627-b7e1ce37feee.png" width =70%>
<image src ="https://user-images.githubusercontent.com/58849278/117758349-d9752300-b25c-11eb-8911-fbe8c20bef1d.png" width = 70%>

call back 

<image src="https://user-images.githubusercontent.com/58849278/118386252-212aee80-b651-11eb-8f95-f347e3cdfc8d.png" width =70%>

(3) 회원가입 통신 

<img src = "https://user-images.githubusercontent.com/58849278/118386419-55eb7580-b652-11eb-95f2-fb54e59338b6.gif" width = "300" height = "500" >

(4)  로그인 통신

<img src = "https://user-images.githubusercontent.com/58849278/118386600-897acf80-b653-11eb-9a68-81259970b2ef.gif" width = "300" height = "500" >

## :whale: Level 2 선택 과제 

**Lv.2 ) 다양한 API 구현하기**

<img src= "https://user-images.githubusercontent.com/58849278/118386712-656bbe00-b654-11eb-9d41-305a3c22726c.png" width =50%>

***사용한 API*** 

 - Github repository 
 - Github user 
 - Github followers
 - Github following 
 - Reqres 더미데이터 서버 (reqres/users)

 <img src ="https://user-images.githubusercontent.com/58849278/118387161-aa452400-b657-11eb-9a27-74cb568046ae.png" width =200 height =400><img src ="https://user-images.githubusercontent.com/58849278/118387200-f4c6a080-b657-11eb-91b7-69b4e7f62c71.png" width =200 height =400><img src ="https://user-images.githubusercontent.com/58849278/118387210-1031ab80-b658-11eb-8cd5-5fdcdd8239cb.png" width =200 height =400>

👉🏻선택과제는 Rxjava로 다음과 같이 구현 (ex. github follower) 

 1. ViewModel 

	<img src="https://user-images.githubusercontent.com/58849278/118387257-80d8c800-b658-11eb-8445-c8be06c48052.png" width =60%>
	

 2. UiState 

	<img src="https://user-images.githubusercontent.com/58849278/118387288-af56a300-b658-11eb-88b5-a4d5a264be8c.png" width =60%>

 3. DiffCallback 

	<img src="https://user-images.githubusercontent.com/58849278/118387363-12e0d080-b659-11eb-8cbf-f40364e099c3.png" width =60%>

:pencil2: 선택과제에서 구현한 5개의 Retrofit2 통신은 위와 같이 구성을 하였습니다. 

<img src = "https://user-images.githubusercontent.com/58849278/118398498-e6967580-b693-11eb-835b-b73dcad03da4.gif" width = "300" height = "500" >

## :whale2: Level 3 성장 과제 

 - 필수 과제에서 사용해준 Callback을 선택과제에서는 Rxjava 문법을 사용하여 구현을 하여 보다 간결한 비동기 통신을 코드를 작성해보고자 하였습니다. 
 <img src="https://user-images.githubusercontent.com/58849278/118387809-81bf2900-b65b-11eb-93f7-05cc59745b57.png" width = 60%>
	<img src="https://user-images.githubusercontent.com/58849278/118387675-ac5cb200-b65a-11eb-9f84-6c9b7668c4d2.png" width = 60%> 
	
 - Singleton을 멀티스레드 환경에서 사용한다고 했을 때 LazyHolder 클래스를 사용하여 thread-safe 하면서도 성능이 저하되지 않고 (synchronized는 성능 저하 가능성) 사용할 수 있습니다. 

## :pencil: 과제를 통해 배우고 성장한 내용 

:paw_prints: 서버 통신은 26기 솝트에서 처음 안드로이드를 접했던 저에게 가장 어려웠던 부분이었습니다. 그래서 4주차 서버통신 세미나 전에 깃허브 레트로핏과 카카오 API를 사용하여 연습을 했음에도, 완전히 알겠다! 이런 느낌이 오지 않아 더 연습을 해야겠다고 생각했습니다.  이번 성장 과제를 하면서 팟장님이 올려주신 API를 다 써서 해보면 나아질 것 같아 하나씩 연습을 해보며 과제를 완성했을 때 뿌듯함을 느꼈습니다. 서버통신 연습을 하면서 자신감이 조금씩 생겼고 특히 전체적인 흐름을 파악하고 구글링을 해서 무작정 따라하는 것보다 이게 왜 이렇게 되는거지 ?? 생각을 하면서 코드를 짜는 것이 도움이 많이 되었습니다. 그래서 도움이 필요한 사람에게 내가 도움이 될 수 있는 OB가 되어야겠다는 생각으로 이번 과제 이후에도 서버 통신 연습을 많이 하려고 합니다 ..😊 앞으로는 보다 깔끔한 코드를 짜는 연습을 더 해서 성장하는 개발자가 되고 싶습니다 ~~! 📘
 

	





