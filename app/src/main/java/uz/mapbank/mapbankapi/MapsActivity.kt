package uz.mapbank.mapbankapi

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import uz.mapbank.mapbankapi.common.lazyFast


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private var hasEnableLocation=false
    private val locationClient by lazyFast { LocationServices.getFusedLocationProviderClient(this) }
    val locationCallback=object :LocationCallback(){
        override fun onLocationResult(p0: LocationResult) {
            super.onLocationResult(p0)
            if (p0==null) return
            val loc=p0.lastLocation
           showLocation(loc)
        }
    }

    private fun showLocation(loc: Location) {
        val sydney = LatLng(loc.latitude, loc.longitude)
        map.addMarker(MarkerOptions().position(sydney).title("Tashkent"))
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.setMinZoomPreference(14.0f)
        map.setMaxZoomPreference(20.0f)


        val sydney = LatLng(41.28760, 69.21898)
        map.addMarker(MarkerOptions().position(sydney).title("Tashkent"))
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        map.animateCamera(CameraUpdateFactory.zoomIn())
    }

    override fun onResume() {
        super.onResume()
        if (!hasEnableLocation){
            hasEnableLocation=true
           if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
               if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                   !=PackageManager.PERMISSION_GRANTED){
                   requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1001)
               }
           }else{
             requestLocation()
           }
        }
    }

    private fun requestLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
             return
        }
        locationClient.locationAvailability
             .addOnSuccessListener {
                 if (it.isLocationAvailable){
                    requestLocations()
                 }else{
                     setUpLocationServise()
                 }
             }
             .addOnFailureListener {
                 setUpLocationServise()
             }
    }

    private fun setUpLocationServise() {
      val request=createLocationClient()
      val settingRequest=LocationSettingsRequest.Builder()
          .addLocationRequest(request)
          .build()
        val settingClient=LocationServices
            .getSettingsClient(this)
            .checkLocationSettings(settingRequest)
            .addOnSuccessListener {  }
            .addOnFailureListener { resolveLocationException(it) }

    }

    private fun createLocationClient(): LocationRequest=LocationRequest().apply {
            this.interval = 10_000
            this.fastestInterval = 5_000
            this.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }


    private fun resolveLocationException(it: Exception) {
        if (it is ResolvableApiException){
          try {
              it.startResolutionForResult(this, 1002)
          }catch (e: Exception){
              e.printStackTrace()
          }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==1002){
            requestLocations()
        }
    }
    private fun requestLocations() {
        val req=createLocationClient()

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        locationClient.requestLocationUpdates(req, locationCallback, null)
    }

    override fun onPause() {
        super.onPause()
        locationClient.removeLocationUpdates(locationCallback)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==1001 && grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            requestLocation()
        }else{

        }
    }
}