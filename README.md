# NoPaginate
Android pagination library, based on [@MarkoMilos](https://github.com/MarkoMilos) repository [Paginate](https://github.com/MarkoMilos/Paginate)

### Gradle

```
compile 'ru.alexbykov:nopaginate:0.4.3'
```

### Install
```java
  Paginate paginate = new PaginateBuilder()
                .with(recyclerView)
                .setCallback(new OnLoadMore() {
                    @Override
                    public void onLoadMore() {
                       // http or db request
                    }
                })
                .setLoadingTriggerThreshold(5)
                .build();
```

### Actions
```java
   paginate.showLoading(show);
   paginate.showError(show);
   paginate.setPaginateNoMoreItems(set);

```

### Custom Loading and Error
For custom error and loaging item just implement the interfaces ```ErrorItem``` or ```LoadingItem```

#####Custom error:

```java
public class CustomError implements ErrorItem {

           @Override
           public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
               View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_error, parent, false);
               return new RecyclerView.ViewHolder(view) {
               };
           }


           @Override
           public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, final OnRepeatListener onRepeatListener) {

               Button btnRepeat = (Button) holder.itemView.findViewById(R.id.btnRepeat);

               btnRepeat.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       if (onRepeatListener != null) {
                           onRepeatListener.onClickRepeat(); //call onLoadMore
                       }
                   }
               });
           }

}


```


#####Custom loading:
```java
public interface LoadingItem extends BaseLinearLayoutManagerItem {


    LoadingItem DEFAULT = new LoadingItem() {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new RecyclerView.ViewHolder(view) {
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }
    };


}
```

#### Install with custom items

```java
  Paginate  paginate = new PaginateBuilder()
                .with(recyclerView)
                .setCallback(new OnLoadMore() {
                    @Override
                    public void onLoadMore() {

                    }
                })
                .setLoadingTriggerThreshold(5)
                .setCustomErrorItem(new CustomError())
                .setCustomLoadingItem(new CustomLoading())
                .build();

```


#### Idea
This repository is a slightly modified version of [Paginate](https://github.com/MarkoMilos/Paginate) library.
Author: [@MarkoMilos](https://github.com/MarkoMilos)

We decided to modify it a little, so that developers could easily use it with MVP or Clean Architecture


#### Todo
1. Double-sided pagination
2. Delegate for ```Presenter``` or ```Interactor```, with implementation Limit/Offset and Page pagination

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