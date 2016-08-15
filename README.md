# Model-View-ViewModel "Hello, World!"

This is a "Hello, World!" project done for a Droidcon Zagreb 2016 talk on [MVVM & RxJava – the perfect mix][droidcon].

The project contains an exemplification of the Model-View-ViewModel pattern used together with RxJava.
A "Hello, World!" greeting will be displayed based on the selected language. 

The `DataModel` provides supported languages and also retrieval of the greeting based on the language.
The `ViewModel` exposes greetings and supported languages as stream of events through [RxJava Observables][observables]. The ViewModel also allows setting the selected language.
The `View` is an Activity that contains a Spinner with the supported languages and a text view that displays a greeting, based on the selected language.


![Model-View-ViewModel](https://github.com/florina-muntenescu/DroidconMVVM/blob/readme/screenshots/mvvm.png?raw=true)

[droidcon]: <http://droidcon.hr/en/sessions/mvvm-rxjava-perfect-mix/>
[observables]: <http://reactivex.io/documentation/observable.html>

