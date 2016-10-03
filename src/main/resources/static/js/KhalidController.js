angular.module('KhalidApp', []).controller(
		'KhalidController',
		function($scope, $http) {
			$scope.greeting = "Airport Details";

			$scope.getAirportInfo = function(keyword) {
				$scope.showSuccess = false;
				console.log('User Entered: ' + keyword);
				// REST Call "GET"
				if (keyword == undefined || keyword.trim() == '') {
					return;
				}
				
				var urlREST = 'http://localhost:8080/lookUpByDescOrCode?keyword=' + keyword;
				
				$http.get(urlREST).then(function(response) {
					$scope.oneAirportDetails = null;
					$scope.allAirports = null;
					
					if (response.data.length == 1) {
						$scope.oneAirportDetails = response.data[0];						
					} else {
						$scope.multiAirportDetails = response.data;
					}
							//$scope.oneAirportDetails = response.data;
							//$scope.allAirports = null;
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
							$scope.oneAirportDetails = response.data;
							$scope.showSuccess = true;
							
							//$scope.myData = response.data;
							console.log('My Response! ' + JSON.stringify(response));
						});
			}
			
			$scope.getAllAirports = function() {
				$scope.airportCode = null; //To clear the field of the airportCode
				console.log('Get All Aiports');
				// REST Call "GET"
				$http.get('http://localhost:8080/getAllAirports').then(function(response) {
							$scope.allAirports = response.data;
							$scope.oneAirportDetails = null;
							// console.log('My Response! ' +
							// JSON.stringify(response));
						});
			}

			$scope.deleteAirport = function(airportCode) {
				console.log('Airport ' + airportCode + " Deleted...");
				$http.delete('http://localhost:8080/deleteAirport?code=' + airportCode).then(
						function(response) {
							$scope.allAirports = response.data;
							$scope.oneAirportDetails = null;
						});
			}
			
			$scope.selectAirport = function(airportCode) {
				console.log('Airport ' + airportCode + " Selected...");
				$http.get('http://localhost:8080/lookUp?code=' + airportCode).then(
						function(response) {
							$scope.oneAirportDetails = response.data;
							$scope.allAirports = null;
						});
			}
		});