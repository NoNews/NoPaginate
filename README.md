# NoPaginate
Android pagination library, based on [@MarkoMilos](https://github.com/MarkoMilos) repository [Paginate](https://github.com/MarkoMilos/Paginate)

### Gradle

```
compile 'ru.alexbykov:nopaginate:0.4.3'
```

### Install
```java
    paginate = new PaginateBuilder()
                .with(recyclerView)
                .setCallback(mainActivityPresenter)
                .setLoadingTriggerThreshold(5)
                .build();

```

### Actions
```java
   paginate.showLoading(show);
   paginate.showError(show);
   paginate.setPaginateNoMoreItems(set)

```


### License
```
Copyright 2017 Alex Bykov
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```