


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

<<<<<<< HEAD
</details>

<details>
		<summary>SignUpActivity </summary>
		
1. SignInActivity와 동일하게 뷰를 구성한다. 
2. 사용자 정보가 부족할 경우 토스트 메세지로 사용자에게 빈칸이 있는지 확인을 하라는 알림을 하고 회원가입을 하며 입력한 사용자의 정보를 SignInActivity에 불러와야 하기 때문에 이때 **putExtra**로 사용자의 깃허브 아이디와 비밀번호를 **intent**로 전달한다. 
		

   

    
	    val intent = Intent()  
	    intent.putExtra("id",signupID.toString())  
    	intent.putExtra("pwd",signupPwd.toString())  
    	setResult(Activity.RESULT_OK, intent)  
    	finish()

</details>

<details>
	<summary>HomeActivity</summary>
	
1. 프로필 사진, 아이디, 이름, 소개 내용이 들어가는 홈 화면 뷰를 앞서 말한 guideline을 사용하여 구성해줬다. 
2. 프로필 사진의 경우 **constraintDimensionRatio**를 사용하여 비율을 1:1로 만들어주었다. 
3. 자기소개 부분 텍스트를 스크롤 하며 볼 수 있도록 **Scroll View**를 사용하여 사용자가 스크롤 할 수 있도록 해주었다. 
</details>


## :pushpin: 기본 과제



 

 :one: **화면전환후 데이터 가져온 로직** 
 화면전환을 할 때, SignUpActivty에서 입력한 사용자의 아이디와 비밀번호를 SignInActivity로 불러와야하기 때문에 onActivityResult 함수를 불러와서 requestCode가 100일 때 Activity.RESULT_OK이면 입력한 String값을 받아오는 것으로 코드를 짜보았다. 
 

        override fun onActivityResult(requestCode : Int, resultCode: Int,data:Intent?){  
	      super.onActivityResult(requestCode, resultCode, data)  
	      if(resultCode == Activity.RESULT_OK){  
	      when(requestCode){  
	      100-> {  
		      binding.editextSigninId.setText(data!!.getStringExtra("id"))  
		      binding.editextSigninPwd.setText(data!!.getStringExtra("pwd")	)  
      
	     } } }}  
	    companion object {  
		      private const val SIGN_UP_RESULT_CODE = 100  
	    }

:two: **Android LifeCycle** 
![image](https://user-images.githubusercontent.com/58849278/113973126-c2669e00-9876-11eb-8759-74195860883b.png)

액티비티가 처음 시작할 때 호출되는 **onCreate**는 사용자 인터페이스를 만드는 일회적 초기화에 사용되는 함수로 null값 또는 onSaveInstanceState() 메서드에 의해 저장된 상태 정보 중 한가지의 매개변수를 갖는다. 
**onStart**는 "액티비티가 사용자에게 곧 보여진다"라는 걸 나타낸다. 
**onResume**은 액티비티가 사용자와 상호작용을 시작 할 수 있을 때 호출되고 애니메이션과 음악을 시작하기 좋은 함수이다. 
**onPause**는 액티비티가 배경으로 전환될 때 사용되는데 다른 액티비티가 이 앞에 위치하게 된다고 보면 될 것 같다. 여기서 중요한 점 ! 다음번에 필요한 데이터는 여기에 저장해두는 것이 좋다. 
**onStop**은 액티비티가 더이상 사용자에게 보여지지 않을 때 호출되며 이때는 한동안 사용되지 않는다. 
**onRestart**는 액티비티가 정지상태에서 다시 디스플레이된다. 
**onDestroy**는 액티비티가 소멸되기 전에 호출되고, 호출되지 않고 시스템이 종료해 버릴 수 있다. 

![image](https://user-images.githubusercontent.com/58849278/113973899-05754100-9878-11eb-971b-aa6b8be456e7.png)
:arrow_right: 다음은 생명주기 로그를 캡쳐한 화면이다. 

:three: **성장한 내용**  :punch:
이번 과제를 통해서 안드로이드 생명주기에 대해 다시 한번 공부할 수 있는 기회가 되어 좋았고, 액티비티가 어떤 식으로 진행이 되는지 알 수 있어서 다음에 코드를 짤 때 이런 부분을 잘 활용할 수 있을 것 같다.  세미나 내용을 다시 복습하면서 만들어보니까 내가 놓치고 있었던 부분들이 많았다는 것을 알게 되었고 더 꼼꼼하게 공부해야겠다는 생각이 들었다. 


## :pushpin: 선택 과제 
:arrow_right: 변수 이름 길어도 모든 사람이 알아볼 수 있도록 써주기 
:arrow_right: Chain 과 GuideLine 이용한 뷰 생성 (위에 설명) 
:arrow_right: ScrollView 사용하여 스크롤 기능 완성해보기 (activity_home 확인) 

:one: **ViewBinding** 
뷰 바인딩 View Binding , 뷰와 상호 작용하는 코드를 보다 쉽게 작성할 수 있는 기능이다. findViewById 메서드를 대체할 수 있고 뷰바인딩은 안스 3.6부터 사용이 가능하다는 사실 ! 
	
:thumbsup: ViewBinding 을 쓰면 좋은 점
	 1. **Null safety** : 뷰에 대한 직접 참조를 생성하여 잘못된 ID로 인한 Null Point Exception 위험이 없다. 또한 뷰가 레이아웃의 일부에만 있는 경우 바인딩 클래스에 해당 참조가 포함된 필드는 @Nullable로 표시된다. 
	 2. **Type safety**: 각 바인딩 클래스의 필드에는 XML 파일에서 참조하는 뷰와 일치하는 타입을 갖는다. 이것은 클래스를 캐스팅할 때 발생할수 있는 오류가 없음을 의미한다. 

:two: **객체지향 프로그래밍 OOP** 
객체지향 프로그래밍은 절차적 프로그래밍 방식과는 다르게 데이터를 추상화시켜 상태와 행위를 가지고 객체들간의 유기적 상호작용을 통한 프로그래밍 방식이라고 생각한다. 클래스와 인스턴스, 추상화 , 캡슐화 , 상속, 다형성 이 5가지 키워드가 객체 지향 프로그래밍을 설명해준다고 생각한다. 최근에 정보처리기사 필기 시험을 봤고 이제 곧 실기 시험을 보는데 가장 많이 나왔던 문제가 **강한 응집력과 약한 결합도 (Strong Cohesion & Weak Coupling)** 이었다. 아직 이런 부분들을 잘 지키면서 코딩하고 있진 않지만 객체 지향적 특성을 생각하면서 코드를 짜다보면 훨씬 깔끔하고 간결한 코드가 될 것 같다. 

:three: **Architecture**
안드로이드 MVC 아키텍쳐는 MODEL, CONTROLLER, VIEW
-   Model은 데이터 로직을 담당, 데이터 처리 담당 
-   View는 사용자의 화면을 담당, UI 처리 ( C로부터 알림을 받고, 이 알림을 M과 함께 UI로 구성)
-   Controller는 비즈니스로직을 담당, 사용자의 이벤트 처리 담당 

:arrow_right: Controller가 사용자 이벤트를 감지하며 데이터의 업데이트 유무를 확인하고 Model이 데이터 갱신 처리후 View에게 자신이 업데이트 되었다는 사실을 알리고! (데이터 전달 아님 !!!)  View는 모델이 업데이트 되었는지 확인 후에 되었을 경우 업데이트 된 데이터를 모델로부터 가져오고 UI 에 갱신해준다. 

## :pushpin: Record 


<img src="https://user-images.githubusercontent.com/56873136/114165056-fd47ff00-9966-11eb-9108-8b1b13dc2896.gif" width = "200" height = "500"><img src="https://user-images.githubusercontent.com/56873136/114165078-05a03a00-9967-11eb-9553-e175976d2450.gif" width = "200" height = "500"><img src="https://user-images.githubusercontent.com/56873136/114165832-f1a90800-9967-11eb-8288-a30c0314642b.gif" width = "200" height = "500">
<<<<<<< HEAD



=======
>>>>>>> 3afeadf4400dfc92d57b1b193ec7a4f77e3c93b6

:heavy_check_mark:**SECOND ASSIGNMENT**

<img width="1591" alt="스크린샷 2021-04-26 오후 2 06 43" src="https://user-images.githubusercontent.com/58849278/116031463-b2352800-a698-11eb-8aa5-df345d83890d.png">

:heavy_check_mark:**OPEN SOURCE LIBRARY**
=======
>>>>>>> 8f011d9cad82defbbd092559a66f4719d1347d98

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
 

	





