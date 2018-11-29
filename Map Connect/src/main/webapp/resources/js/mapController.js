(function() {
  var mapConnectApp = angular.module("mapConnectApp");
  mapConnectApp.controller("mapController", mapController);

  /* Business logic for the map connect controller
     Uses EmailSenderSvc for sending Email and UniqueIDSvc for
     generating a unique ID
  */
  function generateUniqueId() {
    //generates a unique ID to be assigned for each circle
    return Math.random().toString(36).substring(2) +
      (new Date()).getTime().toString(36);
  }


  function mapController($scope, $rootScope, EmailSenderSvc, $anchorScroll, $timeout) {
    // scope variables
    $scope.showCircles = false;
    $scope.currentLatLng = "No location selected";
    $scope.maxCircleRadius = 2000000;
    $scope.inactiveLabelColor = "green";
    $scope.activeLabelColor = "rgb(214, 123, 125)";
    $scope.currentActiveCircleId = "";
    $rootScope.successMessage = "";
    $rootScope.errorMessage = "";
    $rootScope.showLoadingMessage = "";

    $scope.startTour = function() {
      //show a tour of the map connect module
      // clear local storage
      localStorage.clear();
      // Initialize the tour
      tour.init();
      // Start the tour
      tour.start();
    };

    $scope.labelHighlighted = function(id) {
      //logic that highlights the circle label when the corresponding circle is highlighted

      return id == $scope.currentActiveCircleId ? {
        "background-color": $scope.activeLabelColor
      } : {
        "background-color": $scope.inactiveLabelColor
      };
    };
    //set map options
    var options = {
      minZoom: 2,
      maxZoom: 15,
      zoom: 3,
      center: {
        lat: 23.72,
        lng: 77.81
      },
      animation: google.maps.Animation.DROP,
      mapTypeId: google.maps.MapTypeId.HYBRID,
      disableDoubleClickZoom: true
    };

    //show all circles when clicked
    $scope.toggleCircleDetails = function() {
      $scope.showCircles = !$scope.showCircles;
    };

    // New map
    $scope.map = new google.maps.Map(document.getElementById('map'), options);


    //map that stores a name and a corresponding circle object
    $scope.circleMap = {};

    $scope.deleteCircle = function(id) {
      // to delete circle with the id from the map
      $scope.circleMap[id].setMap(null);
      delete $scope.circleMap[id];
      console.log($scope.showCircles());
    };

    $scope.showCircles = function() {
      //shows the details of the circles so far
      var _allCircle = [];
      for (var circ in $scope.circleMap) {
        if ($scope.circleMap.hasOwnProperty(circ)){
        var _objCircle = $scope.circleMap[circ];
        _allCircle.push("Circle: " + _objCircle.uniqueID + " -> " + _objCircle.center + " : " + _objCircle.radius);
      }
      }
      return _allCircle;
    };

    $scope.addCircle = function(coords) {
      //function to add a circle at a location
      //add a basic editable circle
      var uniqueID = generateUniqueId();
      var newCircle = new google.maps.Circle({
        map: $scope.map,
        center: coords,
        radius: 500000,
        fillColor: $scope.inactiveLabelColor,
        editable: true,
        uniqueID: uniqueID,
        numberOfPeople: 0 //number of people that would be filled by the response
      });
      $scope.$evalAsync(function() {
        $scope.circleMap[newCircle.uniqueID] = newCircle;
      });

      //double clicking inside the circle should delete it, from the view and cache as well
      google.maps.event.addListener(newCircle, 'dblclick', function(event) {
        newCircle.setMap(null);
        delete $scope.circleMap[newCircle.uniqueID];
        $scope.$evalAsync();
        console.log($scope.showCircles());
      });

      //Update circle center when it is dragged
      google.maps.event.addListener(newCircle, 'center_changed', function(event) {
        $scope.$evalAsync();
      });

      //Update circle radius when it is changed
      google.maps.event.addListener(newCircle, 'radius_changed', function(event) {
        //limit circle radius to a maxRadius value
        var actual_radius = (Math.min(newCircle.radius, $scope.maxCircleRadius));
        this.radius = actual_radius;
        $scope.$evalAsync();
      });

      //Todo: Update color of active circle and select it in the panel too
      google.maps.event.addListener(newCircle, 'mouseover', function(event) {
        newCircle.setOptions({
          fillColor: 'red'
        });
        //since we are inside a circle, set active circle label
        $scope.currentActiveCircleId = newCircle.uniqueID;
        $scope.$evalAsync();
        //scroll to the corresponding item
        $anchorScroll($scope.currentActiveCircleId);
      });

      //Todo: restore color of inactive circle and select it in the panel too
      google.maps.event.addListener(newCircle, 'mouseout', function(event) {
        newCircle.setOptions({
          fillColor: 'green'
        });
        //since we are out of a circle, set active circle as None
        $scope.currentActiveCircleId = "";
        $scope.$evalAsync();
      });

      //since we added a circle, set active circle label so that it appears highlighted
      $scope.currentActiveCircleId = newCircle.uniqueID;
      google.maps.event.trigger(newCircle, 'mouseover', {});
      $scope.$evalAsync();
    };

    //double click on the map should add a circle
    google.maps.event.addListener($scope.map, 'dblclick', function(event) {
      $scope.addCircle(event.latLng);
      console.log($scope.showCircles());
    });

    //Display the latitude and longitude wherever clicked
    google.maps.event.addListener($scope.map, 'click', function(event) {
      $scope.currentLatLng = event.latLng;
      $scope.$evalAsync();
    });

    //function to execute email click logic

    $scope.circleLabelMouseEnter = function(id) {
      // Highlight the circle in the map with the given id
      $scope.circleMap[id].setOptions({
        fillColor: $scope.activeLabelColor
      });
      //also Highlight the label
      $scope.currentActiveCircleId = id;
      $scope.$evalAsync();
      //also center the google map to the required circle center
      $scope.map.panTo($scope.circleMap[id].center);
    };

    $scope.circleLabelMouseLeave = function(id) {
      // Highlight the circle in the map with the given id
      $scope.circleMap[id].setOptions({
        fillColor: 'green'
      });
      $scope.currentActiveCircleId = "";
      $scope.$evalAsync();
    };

    $scope.verifyEmailSubjectText = function() {
      //verify if the user has entered both email subject and content
      //if true, only then enable the send email button
      var data = $scope.fields;
      if (typeof data === "undefined")
        return false;
      if (typeof data.subject === "undefined" || data.subject === "")
        return false;
      if (typeof data.content === "undefined"  || data.content === "")
        return false;
      return true;
    };

    $scope.sendEmail = function() {
      //prepare json representation of
      var circleList = [];
      var circleProperties = {};

      for (var circle in $scope.circleMap) {
        if ($scope.circleMap.hasOwnProperty(circle)){
        var circleObj = $scope.circleMap[circle];
          circleProperties.lat = circleObj.center.lat();
          circleProperties.lng = circleObj.center.lng();
          circleProperties.radius = circleObj.radius / 1000.0;
          circleProperties.id = circleObj.uniqueID;

          circleList.push(circleProperties);
          circleProperties = {};
      	}
      }
      //get mail subject and content
      var data = $scope.fields;
      if (data === undefined) {
        data = {};
      }

      var requestJSON = {};
      requestJSON.message = data;
      requestJSON.circles = circleList;

      EmailSenderSvc.sendMailUtil(requestJSON).then(function(response) {
        // The response object has these properties:
        //set success callback
        $rootScope.successMessage = "Successful request: (" + response.status + ")";
        $rootScope.errorMessage = "";
        $rootScope.showLoadingMessage = '';
        $timeout(function(){
          //stop displaying the success message
          $rootScope.successMessage = '';
        }, 5000);

         for (circle in response.data){
           //this is an object with id and count keys;
             var circleObj = response.data[circle];
             $scope.circleMap[circleObj.id].numberOfPeople = circleObj.numberOfPeople;
             //alert("For Circle: " + circleObj.id + " we have: " + circleObj.numberOfPeople);
           
         }

      }, function(response) {
        //set error callback message
        $rootScope.successMessage = "";
        // $rootScope.showLoadingMessage = '';
        $rootScope.showLoadingMessage = '';
        $rootScope.errorMessage = "Error sending request (" + response.status + ")";
        // alert($rootScope.errorMessage);
        $timeout(function(){
          //stop displaying the error message
          $rootScope.errorMessage = '';
        }, 5000);
      });
    };
  }

})();
