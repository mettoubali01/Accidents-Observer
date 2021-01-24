const geoJsonEndpoints = "http://localhost:8081/api/geojson/";

async function main(){

    mapboxgl.accessToken = 'your-api-key';
    var map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/light-v10',
        center: [4.8380649, 39.3262345],
        zoom : 3
    });

    const response_geojson = await  fetch(geoJsonEndpoints)
    console.log("1")
    console.log(response_geojson)
    var geojson = await response_geojson.json();
    console.log("2")
    console.log(geojson)
    // add markers to map
    geojson.features.forEach(function(marker) {
        // create a HTML element for each feature
        var el = document.createElement('div');
        el.className = 'marker';
        // make a marker for each feature and add to the map
        new mapboxgl.Marker(el)
            .setLngLat(marker.geometry.coordinates)
            .setPopup(new mapboxgl.Popup({ offset: 25 }) // add popups
                .setHTML('<h3>' + marker.properties.title + '</h3>' +
                    '<a href=\"' + marker.properties.description + '\">' +
                    'See the tweet on twitter</a>'))
            .addTo(map);
    });
}

main().catch(err =>
    console.error(err))