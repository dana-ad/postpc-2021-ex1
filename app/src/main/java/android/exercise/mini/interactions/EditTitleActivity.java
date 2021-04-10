package android.exercise.mini.interactions;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditTitleActivity extends AppCompatActivity {

  private boolean isEditing = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_title);

    // find all views
    FloatingActionButton fabStartEdit = findViewById(R.id.fab_start_edit);
    FloatingActionButton fabEditDone = findViewById(R.id.fab_edit_done);
    TextView textViewTitle = findViewById(R.id.textViewPageTitle);
    EditText editTextTitle = findViewById(R.id.editTextPageTitle);

    // setup - start from static title with "edit" button
    fabStartEdit.setVisibility(View.VISIBLE);
    fabEditDone.setVisibility(View.GONE);
    textViewTitle.setText("Page title here");
    textViewTitle.setVisibility(View.VISIBLE);
    editTextTitle.setText("Page title here");
    editTextTitle.setVisibility(View.GONE);


    // handle clicks on "start edit"
    fabStartEdit.setOnClickListener(v -> {
              this.isEditing = true;
              fabStartEdit.setVisibility(View.GONE);
              fabStartEdit.animate().alpha(0f);
              fabEditDone.setVisibility(View.VISIBLE);
              fabEditDone.animate().alpha(1f);
              textViewTitle.setVisibility(View.GONE);
              editTextTitle.setVisibility(View.VISIBLE);
              editTextTitle.setText(textViewTitle.getText());
      });

    // handle clicks on "done edit"
    fabEditDone.setOnClickListener(v -> {
      this.isEditing = false;
      fabEditDone.setVisibility(View.GONE);
      fabEditDone.animate().alpha(0f);
      fabStartEdit.setVisibility(View.VISIBLE);
      fabStartEdit.animate().alpha(1f);
      textViewTitle.setText(editTextTitle.getText());
      textViewTitle.setVisibility(View.VISIBLE);
      editTextTitle.setVisibility(View.GONE);
      try {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(textViewTitle.getWindowToken(), 0);
      } catch (Exception e) {
        e.printStackTrace();
      }

    });
  }

  @Override
  public void onBackPressed() {
    EditText editTextTitle = findViewById(R.id.editTextPageTitle);
    TextView textViewTitle = findViewById(R.id.textViewPageTitle);
    FloatingActionButton fabStartEdit = findViewById(R.id.fab_start_edit);
    FloatingActionButton fabEditDone = findViewById(R.id.fab_edit_done);
    if (this.isEditing) {
      editTextTitle.setVisibility(View.GONE);
      editTextTitle.setText("Page title here");
      textViewTitle.setText("Page title here");
      textViewTitle.setVisibility(View.VISIBLE);
      fabEditDone.setVisibility(View.GONE);
      fabEditDone.animate().alpha(0f);
      fabStartEdit.setVisibility(View.VISIBLE);
      fabStartEdit.animate().alpha(1f);
    }
    else {
      super.onBackPressed();
    }
    
  }
}