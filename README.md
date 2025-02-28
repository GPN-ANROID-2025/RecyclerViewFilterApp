﻿# RecyclerViewFilterApp

```markdown
# Filtering RecyclerView Results in Java (Android)

## 1. Implement Filterable in Adapter
Modify your `RecyclerView.Adapter` to implement `Filterable`.

```java
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements Filterable {
    private List<String> fullList;
    private List<String> filteredList;

    public MyAdapter(List<String> list) {
        this.fullList = new ArrayList<>(list);
        this.filteredList = list;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<String> filteredResults = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredResults.addAll(fullList);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (String item : fullList) {
                        if (item.toLowerCase().contains(filterPattern)) {
                            filteredResults.add(item);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredResults;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList.clear();
                filteredList.addAll((List<String>) results.values);
                notifyDataSetChanged();
            }
        };
    }
}
```

## 2. Implement Search in Activity/Fragment
Call `getFilter().filter(query)` when text changes in a `SearchView` or `EditText`.

```java
searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
    @Override
    public boolean onQueryTextSubmit(String query) {
        myAdapter.getFilter().filter(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        myAdapter.getFilter().filter(newText);
        return false;
    }
});
```

