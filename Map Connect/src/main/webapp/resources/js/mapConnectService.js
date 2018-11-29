(function(){
  var mapConnectApp = angular.module("mapConnectApp");

  // This service sends a put request to the API
  // Returns the promise
  mapConnectApp.service("EmailSenderSvc", function($http, $rootScope){
    var self = this;
    var config = {};
     //$http.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
      // Send this header only in post requests. Specifies you are sending a JSON object
        //$http.defaults.headers.post['dataType'] = 'json';

    self.sendMailUtil = function(data){

      //clear all success and error messages
      $rootScope.successMessage = "";
      $rootScope.errorMessage = "";
      var promise = $http.post("http://localhost:8080/ims/api/circles", data, config);
      //hide the modal and show loading msg
      $(".modal").modal("hide");
      $rootScope.showLoadingMessage = "loading";
      return promise;
    };
  });
})();
