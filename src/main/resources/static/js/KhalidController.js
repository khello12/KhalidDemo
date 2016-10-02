angular.module('KhalidApp', []).controller(
		'KhalidController',
		function($scope, $http) {
			$scope.greeting = "Airport Details";

			$scope.getAirportInfo = function(airportCode) {
				console.log('User Entered: ' + airportCode);
				// REST Call "GET"
				$http.get('http://localhost:8080/lookUp?code=' + airportCode)
						.then(function(response) {
							$scope.myData = response.data;
							$scope.allAirports = null;
							// console.log('My Response! ' +
							// JSON.stringify(response));
						});
			}

			$scope.updatePhone = function(phoneNo, airportCode) {
				console.log('User Entered Phone Number: ' + phoneNo
						+ " for Airport Code: " + airportCode);
				// REST Call "POST"
				$http.post('http://localhost:8080/enterInfo?phone=' + phoneNo + '&code=' + airportCode).then(
						function(response) {
							//$scope.myData = response.data;
							 console.log('My Response! ' + JSON.stringify(response));
						});
			}
			
			$scope.getAllAirports = function() {
				console.log('Get All Aiports');
				// REST Call "GET"
				$http.get('http://localhost:8080/getAll').then(function(response) {
							$scope.allAirports = response.data;
							$scope.myData = null;
							// console.log('My Response! ' +
							// JSON.stringify(response));
						});
			}

			$scope.deleteAirport = function(airportCode) {
				console.log('Airport ' + airportCode + " Deleted...");
				$http.delete('http://localhost:8080/deleteAirport?code=' + airportCode).then(
						function(response) {
							$scope.allAirports = response.data;
							$scope.myData = null;
						});
			}
		});