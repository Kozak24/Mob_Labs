package kozak.labs.Model;

import java.util.List;

import kozak.labs.Entity.Character;
import kozak.labs.Entity.Characters;
import kozak.labs.MVPInterfaces.ListFragmentContract;
import kozak.labs.Retrofit.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListModel implements ListFragmentContract.Model {
    private List<Character> charactersList;

    @Override
    public List<Character> makeCall() {
        ApiClient.getApiService().getData().clone().enqueue( new Callback<Characters>() {
            @Override
            public void onResponse(Call<Characters> call, Response<Characters> response) {
                if (response.body() != null) {
                    charactersList = response.body().getCharacters();
                } else {
                    charactersList = null;
                }
             }

            @Override
            public void onFailure(Call<Characters> call, Throwable t) {
                charactersList = null;
            }
        });
        return charactersList;
    }
}
