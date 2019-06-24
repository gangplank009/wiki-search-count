package com.android.gangplank.wikisearchcount;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

public class MainSearchFragmentDirections {
  private MainSearchFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionMainSearchFragmentToSearchHistoryFragment() {
    return new ActionOnlyNavDirections(R.id.action_mainSearchFragment_to_searchHistoryFragment);
  }
}
