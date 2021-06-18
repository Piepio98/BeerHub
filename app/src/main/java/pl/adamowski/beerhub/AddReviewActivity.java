package pl.adamowski.beerhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import pl.adamowski.beerhub.models.Review;
import pl.adamowski.beerhub.repository.ReviewDao;

public class AddReviewActivity extends AppCompatActivity {

    private EditText name;
    private EditText brewery;
    private EditText style;
    private EditText comment;
    //private RatingBar rBar;

    String nameString;
    String breweryString;
    String styleString;
    String commentString;
    //Float ratingFloat;
    //float rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View add = findViewById(R.id.add_review_confirm);



        add.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View V) {
                                       addReview();
                                   }
                               }
        );
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addReview() {

        name = (EditText) findViewById(R.id.add_review_name);
        brewery = (EditText) findViewById(R.id.add_review_brewery);
        style = (EditText) findViewById(R.id.add_review_style);
        comment = (EditText) findViewById(R.id.add_review_comment);
        //rBar = (RatingBar) findViewById(R.id.rating_bar);

        nameString = name.getText().toString();
        breweryString = brewery.getText().toString();
        styleString = style.getText().toString();
        commentString = comment.getText().toString();
        //rating = rBar.getRating();
        //ratingFloat = rating;


        if(nameString.length() <=0 || breweryString.length() <=0 || styleString.length() <=0 || commentString.length() <=0)
        {
            if(nameString.length() <= 0) name.setError("Text can not be empty");
            if(breweryString.length() <= 0) brewery.setError("Text can not be empty");
            if(styleString.length() <= 0) style.setError("Text can not be empty");
            if(commentString.length() <= 0) comment.setError("Text can not be empty");
        }

        else {
            Review review = new Review(null, nameString, breweryString, styleString, commentString);
            BeerDataBase database = BeerApplication.getINSTANCE();

            ReviewDao reviewDao = database.reviewDao();
            long id = reviewDao.saveInDataBase(review);
            Toast.makeText(this, "Dodano nową recenzję nr: " + id, Toast.LENGTH_SHORT).show();

            name.setText("");
            brewery.setText("");
            style.setText("");
            comment.setText("");
        }
    }
}