package net.sytes.smart_tracking;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.w3c.dom.Text;

public class NewServiceActivity extends AppCompatActivity implements OnMapReadyCallback {

    private View buttonNSContinue;
    private View imageViewAsButton;
    private TextView transportID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_service);

        buttonNSContinue = (Button) findViewById(R.id.buttonNSContinue);
        imageViewAsButton = (ImageView) findViewById(R.id.imageScanner);
        transportID = (TextView) findViewById(R.id.transportID);

        imageViewAsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanCode();
            }
        });
        buttonNSContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewServiceActivity.this, SListActivity.class);
                startActivity(intent);
            }
        });

    }

    private void ScanCode(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureActivity.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Escaneando");
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result != null)
        {
            if (result.getContents() != null)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Resultado");
                builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        transportID.setText("ID: "+result.getContents().toString());
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ScanCode();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else
            {
                Toast.makeText(this,"No se encontraron resultados", Toast.LENGTH_SHORT).show();
            }
        }else
        {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

    MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapFragment.getMapAsync(this);
    }
}
