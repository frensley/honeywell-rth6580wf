/**
 *  Total Comfort 9580b API
 *   
 *  Based on Code by Eric Thomas
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
preferences {
    input("username", "text", title: "Username", description: "Your Total Comfort User Name")
    input("password", "password", title: "Password", description: "Your Total Comfort password")
    input("honeywelldevice", "text", title: "Device ID", description: "Your Device ID")
    
}
metadata {
  definition (name: "Total Comfort 9580a API", namespace: "Total Comfort 9580a API", author: "Bob Hudson") {
    capability "Polling"
    capability "Thermostat"
    capability "Refresh"
    capability "Temperature Measurement"
    capability "Sensor"
    capability "Relative Humidity Measurement"    
    command "heatLevelUp"
    command "heatLevelDown"
    command "coolLevelUp"
    command "coolLevelDown"
  }

  simulator {
    // TODO: define status and reply messages here
  }

   tiles {
        valueTile("temperature", "device.temperature",) {
            state("temperature", label: '${currentValue}°F', unit:"F", icon:"http://www.waltontaxpayers.org/Icons/Tempinside2.png",
            backgroundColors: [
                    [value: 31, color: "#214478"],
                    [value: 44, color: "#214478"],
                    [value: 59, color: "#214478"],
                    [value: 74, color: "#214478"],
                    [value: 84, color: "#214478"],
                    [value: 95, color: "#214478"],
                    [value: 96, color: "#214478"]
                ]
            )
        }
        standardTile("thermostatMode", "device.thermostatMode", inactiveLabel: false, canChangeIcon: true, decoration: "flat") {
            state "heat", label:'${name}', action:"thermostat.off", icon: "http://www.waltontaxpayers.org/Icons/Heating.png"
            state "off", label:'${name}', action:"thermostat.cool", icon: "http://www.waltontaxpayers.org/Icons/Unitturnedoff2.png"
            state "cool", label:'${name}', action:"thermostat.emergencyHeat", icon: "http://www.waltontaxpayers.org/Icons/Cooling.png"
            state "emerg", label:'${name}', action:"thermostat.heat", icon: "http://www.waltontaxpayers.org/Icons/EmergHeat.png"
    
        }
        standardTile("thermostatFanMode", "device.thermostatFanMode", inactiveLabel: false, canChangeIcon: true, decoration: "flat") {
            state "on", label:'${name}', action:"thermostat.fanAuto", icon: "http://www.waltontaxpayers.org/Icons/FanOn.png"
            state "auto", label:'${name}', action:"thermostat.fanCirculate", icon: "http://www.waltontaxpayers.org/Icons/Fan.png"
            state "circ",label:'${name}', action:"thermostat.fanOn", icon: "http://www.waltontaxpayers.org/Icons/FanCirc.png"}
        	

        controlTile("coolSliderControl", "device.coolingSetpoint", "slider", height: 3, width: 1, inactiveLabel: false) {
            state "setCoolingSetpoint", label:'Set temperarure to', action:"thermostat.setCoolingSetpoint", 
            backgroundColors:[
            [value: 31, color: "#214478"],
            [value: 44, color: "#214478"],
            [value: 59, color: "#214478"],
            [value: 74, color: "#214478"],
            [value: 84, color: "#214478"],
            [value: 95, color: "#214478"],
            [value: 96, color: "#214478"]
            ]               
        }
       valueTile("coolingSetpoint", "device.coolingSetpoint", inactiveLabel: false) 
    	  {
          state "default", label:' ${currentValue}°', icon:"http://www.waltontaxpayers.org/Icons/Coolingset1.png",
           backgroundColors:
           [
            [value: 31, color: "#214478"],
            [value: 44, color: "#214478"],
            [value: 59, color: "#214478"],
            [value: 74, color: "#214478"],
            [value: 84, color: "#214478"],
            [value: 95, color: "#214478"],
            [value: 96, color: "#214478"]
          ]   
        }
        valueTile("heatingSetpoint", "device.heatingSetpoint", inactiveLabel: false) 
    	{
      state "default", label: ' ${currentValue}°', icon:"http://www.waltontaxpayers.org/Icons/Heatingset1.png",
       backgroundColors:
       [
        [value: 31, color: "#214478"],
        [value: 44, color: "#214478"],
        [value: 59, color: "#214478"],
        [value: 74, color: "#214478"],
        [value: 84, color: "#214478"],
        [value: 95, color: "#214478"],
        [value: 96, color: "#214478"]
      ]   
    }
    
        
        //tile added for operating state - Create the tiles for each possible state, look at other examples if you wish to change the icons here. 
        
         standardTile("thermostatOperatingState", "device.thermostatOperatingState", inactiveLabel: false) {
            state "schedule", label:'${name}', icon: "http://www.waltontaxpayers.org/Icons/statefs.png"
            state "t hold", label:'${name}', icon: "http://www.waltontaxpayers.org/Icons/stateth.png"
            state "p hold", label:'${name}', icon: "http://www.waltontaxpayers.org/Icons/stateph.png"
            
        }
        
        valueTile("changeCycle", "device.changeCycle", inactiveLabel: false) {
            state "default", label:'${currentValue}', icon: "http://www.waltontaxpayers.org/Icons/HoldTime3.png",
       backgroundColors:
       [
        [value: 31, color: "#214478"],
        [value: 44, color: "#214478"],
        [value: 59, color: "#214478"],
        [value: 74, color: "#214478"],
        [value: 84, color: "#214478"],
        [value: 95, color: "#214478"],
        [value: 96, color: "#214478"]
      ]   
        }
        standardTile("refresh", "device.thermostatMode", inactiveLabel: false, decoration: "flat") {
            state "default", action:"polling.poll", icon: "http://www.waltontaxpayers.org/Icons/Refresh2.png"
        }
        
        standardTile("heatLevelUp", "device.heatingSetpoint", canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
                        state "heatLevelUp", label:'Up', action:"heatLevelUp", icon:"http://www.waltontaxpayers.org/Icons/Heatup1.png"
        }
        standardTile("heatLevelDown", "device.heatingSetpoint", canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
                        state "heatLevelDown", label:'Down', action:"heatLevelDown", icon:"http://www.waltontaxpayers.org/Icons/Heatdown1.png"
        }
        standardTile("coolLevelUp", "device.heatingSetpoint", canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
                        state "coolLevelUp", label:'Up', action:"coolLevelUp", icon:"http://www.waltontaxpayers.org/Icons/Coolup1.png"
        }
        standardTile("coolLevelDown", "device.heatingSetpoint", canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
                        state "coolLevelDown", label:'Down', action:"coolLevelDown", icon:"http://www.waltontaxpayers.org/Icons/Cooldown1.png"
       }
        
        valueTile("outdoorHumid", "device.outdoorHumid", inactiveLabel: false){
        	state "default", label:' ${currentValue}%', unit:"%", icon:"http://www.waltontaxpayers.org/Icons/Outsidehumid1.png",
            backgroundColors:
           [
            [value: 30, color: "#214478"],
            [value: 40, color: "#214478"],
            [value: 50, color: "#214478"],
            [value: 60, color: "#214478"],
            [value: 70, color: "#214478"],
            [value: 80, color: "#214478"],
            [value: 90, color: "#214478"]
          ]   
        	
		}
        
        valueTile("outdoorTemp", "device.outdoorTemp", inactiveLabel: false){
        	state "default", label:'${currentValue}°', icon:"http://www.waltontaxpayers.org/Icons/Outsidetemp1.png",
            backgroundColors:
           [
            [value: 30, color: "#214478"],
            [value: 40, color: "#214478"],
            [value: 50, color: "#214478"],
            [value: 60, color: "#214478"],
            [value: 70, color: "#214478"],
            [value: 80, color: "#214478"],
            [value: 90, color: "#214478"]
          ]   
        }
        
        valueTile("relativeHumidity", "device.relativeHumidity", inactiveLabel: false){
        	state "default", label:'${currentValue}%', unit:"%", icon:"http://www.waltontaxpayers.org/Icons/Insidehumid1.png",
            backgroundColors:
           [
            [value: 30, color: "#214478"],
            [value: 40, color: "#214478"],
            [value: 50, color: "#214478"],
            [value: 60, color: "#214478"],
            [value: 70, color: "#214478"],
            [value: 80, color: "#214478"],
            [value: 90, color: "#214478"]
          ]   
        	
		}
        
        
        
        main "temperature"
        details(["thermostatMode","temperature", "thermostatFanMode",   "heatLevelUp", "heatingSetpoint" , "heatLevelDown", "coolLevelUp","coolingSetpoint","coolLevelDown" ,"thermostatOperatingState","changeCycle","refresh","relativeHumidity" ,"outdoorTemp","outdoorHumid"])
} 
}

def coolLevelUp(){
    int nextLevel = device.currentValue("coolingSetpoint") + 1
    
    if( nextLevel > 99){
      nextLevel = 99
    }
    log.debug "Setting cool set point up to: ${nextLevel}"
    setCoolingSetpoint(nextLevel)
}

def coolLevelDown(){
    int nextLevel = device.currentValue("coolingSetpoint") - 1
    
    if( nextLevel < 50){
      nextLevel = 50
    }
    log.debug "Setting cool set point down to: ${nextLevel}"
    setCoolingSetpoint(nextLevel)
}

def heatLevelUp(){
    int nextLevel = device.currentValue("heatingSetpoint") + 1
    
    if( nextLevel > 90){
      nextLevel = 90
    }
    log.debug "Setting heat set point up to: ${nextLevel}"
    setHeatingSetpoint(nextLevel)
}

def heatLevelDown(){
    int nextLevel = device.currentValue("heatingSetpoint") - 1
    
    if( nextLevel < 40){
      nextLevel = 40
    }
    log.debug "Setting heat set point down to: ${nextLevel}"
    setHeatingSetpoint(nextLevel)
}



// parse events into attributes
def parse(String description) {
    
}

// handle commands
def setHeatingSetpoint(temp) {
  data.SystemSwitch = 'null' 
    data.HeatSetpoint = temp
    data.CoolSetpoint = 'null'
    data.HeatNextPeriod = 'null'
    data.CoolNextPeriod = 'null'
    data.StatusHeat='1'
    data.StatusCool='1'
    data.FanMode = 'null'
  setStatus()

    if(data.SetStatus==1)
  {
        sendEvent(name: 'heatingSetpoint', value: temp as Integer)

    }
        
}

def setCoolingSetpoint(temp) {
  data.SystemSwitch = 'null' 
    data.HeatSetpoint = 'null'
    data.CoolSetpoint = temp
    data.HeatNextPeriod = 'null'
    data.CoolNextPeriod = 'null'
    data.StatusHeat='1'
    data.StatusCool='1'
    data.FanMode = 'null'
  setStatus()
    
    if(data.SetStatus==1)
  {
        sendEvent(name: 'coolingSetpoint', value: temp as Integer)

    }
}

def setTargetTemp(temp) {
  data.SystemSwitch = 'null' 
    data.HeatSetpoint = temp
    data.CoolSetpoint = temp
    data.HeatNextPeriod = 'null'
    data.CoolNextPeriod = 'null'
    data.StatusHeat='1'
    data.StatusCool='1'
    data.FanMode = 'null'
  setStatus()
}

def off() {
  setThermostatMode(2)
}

def heat() {
  setThermostatMode(1)
}

def auto() {
  setThermostatMode(4)
}

def emerg() {
  setThermostatMode(0)
}

def cool() {
  setThermostatMode(3)
}

def setThermostatMode(mode) {
  data.SystemSwitch = mode 
    data.HeatSetpoint = 'null'
    data.CoolSetpoint = 'null'
    data.HeatNextPeriod = 'null'
    data.CoolNextPeriod = 'null'
    data.StatusHeat=1
    data.StatusCool=1
    data.FanMode = 'null'

  setStatus()
    
      def switchPos

        if(mode==0)
          switchPos = 'emerg'
        if(mode==1)
          switchPos = 'heat'
        if(mode==2)
          switchPos = 'off'
        if(mode==3)
          switchPos = 'cool'
          
  if(data.SetStatus==1)
  {
        sendEvent(name: 'thermostatMode', value: switchPos)
    }
    
}

def fanAuto() {
    setThermostatFanMode(0)
}

def fanOn() {
    setThermostatFanMode(1)
}

def fanCirculate() {
    setThermostatFanMode(2)
}


def setThermostatFanMode(mode) {    
  
  data.SystemSwitch = 'null' 
    data.HeatSetpoint = 'null'
    data.CoolSetpoint = 'null'
    data.HeatNextPeriod = 'null'
    data.CoolNextPeriod = 'null'
    data.StatusHeat='null'
    data.StatusCool='null'
    data.FanMode = mode

  setStatus()

  def fanMode

    if(mode==0)
      fanMode = 'auto'
    if(mode==1)
      fanMode = 'on'
    if(mode==2)
      fanMode= 'circ'

  if(data.SetStatus==1)
  {
      sendEvent(name: 'thermostatFanMode', value: fanMode)    
    }

}


def poll() {
refresh()
}
def setStatus() {

  data.SetStatus = 0

    login()
  log.debug "Executing 'setStatus'"
def today= new Date()
log.debug "https://mytotalconnectcomfort.com/portal/Device/SubmitControlScreenChanges"

    
    def params = [
        uri: "https://mytotalconnectcomfort.com/portal/Device/SubmitControlScreenChanges",
        headers: [
              'Accept': 'application/json, text/javascript, */*; q=0.01',
              'DNT': '1',
        	  'Accept-Encoding': 'gzip,deflate,sdch',
              'Cache-Control': 'max-age=0',
              'Accept-Language': 'en-US,en,q=0.8',
              'Connection': 'keep-alive',
              'Host': 'rs.alarmnet.com',
              'Referer': "https://mytotalconnectcomfort.com/portal/Device/Control/${settings.honeywelldevice}",
              'X-Requested-With': 'XMLHttpRequest',
              'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36',
              'Cookie': data.cookiess        ],
        body: [ DeviceID: "${settings.honeywelldevice}", SystemSwitch : data.SystemSwitch ,HeatSetpoint : data.HeatSetpoint, CoolSetpoint: data.CoolSetpoint, HeatNextPeriod: data.HeatNextPeriod,CoolNextPeriod:data.CoolNextPeriod,StatusHeat:data.StatusHeat,StatusCool:data.StatusCool,FanMode:data.FanMode]

]

    httpPost(params) { response ->
        log.debug "Request was successful, $response.status"
 
    }
    
    log.debug "SetStatus is 1 now"
    data.SetStatus = 1
    
}

def getStatus() {
  log.debug "Executing 'getStatus'"
def today= new Date()
log.debug "https://mytotalconnectcomfort.com/portal/Device/CheckDataSession/${settings.honeywelldevice}?_=$today.time"



    def params = [
        uri: "https://mytotalconnectcomfort.com/portal/Device/CheckDataSession/${settings.honeywelldevice}",
        headers: [
              'Accept': '*/*',
              'DNT': '1',
              'Accept-Encoding': 'plain',
              'Cache-Control': 'max-age=0',
              'Accept-Language': 'en-US,en,q=0.8',
              'Connection': 'keep-alive',
              'Host': 'rs.alarmnet.com',
              'Referer': 'https://mytotalconnectcomfort.com/portal',
              'X-Requested-With': 'XMLHttpRequest',
              'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36',
              'Cookie': data.cookiess        ],
    ]

        httpGet(params) { response ->
        log.debug "Request was successful, $response.status"

        
        def curTemp = response.data.latestData.uiData.DispTemperature
        def fanMode = response.data.latestData.fanData.fanMode
        def switchPos = response.data.latestData.uiData.SystemSwitchPosition
        def coolSetPoint = response.data.latestData.uiData.CoolSetpoint
        def heatSetPoint = response.data.latestData.uiData.HeatSetpoint
        def statusCool = response.data.latestData.uiData.StatusCool
        def statusHeat = response.data.latestData.uiData.StatusHeat
        def curHumidity = response.data.latestData.uiData.IndoorHumidity
        def changeCycle = response.data.latestData.uiData.HeatNextPeriod
        def outdoorTemp = response.data.latestData.uiData.OutdoorTemperature
        def outdoorHumid = response.data.latestData.uiData.OutdoorHumidity


        log.trace("IndoorHumidity: ${response.data.latestData.uiData.IndoorHumidity}")
        log.trace("IndoorHumiditySensorAvailable: ${response.data.latestData.uiData.IndoorHumiditySensorAvailable}")        
        log.trace("IndoorHumiditySensorNotFault: ${response.data.latestData.uiData.IndoorHumiditySensorNotFault}")        
        log.trace("IndoorHumidStatus: ${response.data.latestData.uiData.IndoorHumidStatus}")
        log.trace("StatusHeat: ${response.data.latestData.uiData.StatusHeat}")
        log.trace("StatusCool: ${response.data.latestData.uiData.StatusCool}")
        log.trace("HeatNextPeriod: ${response.data.latestData.uiData.HeatNextPeriod}")
        log.trace("outdoorTemp: ${response.data.latestData.uiData.OutdoorTemperature}")
        log.trace("outdoorHumid: ${response.data.latestData.uiData.OutdoorHumidity}")
        
        def operatingState = "off"
        if (statusHeat == 0 ) {
            operatingState = "schedule"}
        else if (statusHeat == 1 ) {
            operatingState = "t hold"} 
        else if (statusHeat == 2 ) {
            operatingState = "p hold"}     
        else if (statusCool == 0 ) {
            operatingState = "schedule"}
        else if (statusCool == 1 ) {
            operatingState = "t hold"} 
        else if (statusCool == 2 ) {
            operatingState = "p hold"}   
            else {
          	operatingState = "off"
        }
        
        
        if (changeCycle == 1)
        {changeCycle = "12:15am"}
			else if (changeCycle == 2)
        {changeCycle = "12:30am"}
			else if (changeCycle == 3)
        {changeCycle = "12:45am"}
			else if (changeCycle == 4)
        {changeCycle = "1:00am"}
			else if (changeCycle == 5)
        {changeCycle = "1:15am"}
			else if (changeCycle == 6)
        {changeCycle = "1:30am"}
			else if (changeCycle == 7)
        {changeCycle = "1:45am"}
			else if (changeCycle == 8)
        {changeCycle = "2:00am"}
			else if (changeCycle == 9)
        {changeCycle = "2:15am"}
			else if (changeCycle == 10)
        {changeCycle = "2:30am"}
			else if (changeCycle == 11)
        {changeCycle = "2:45am"}
			else if (changeCycle == 12)
        {changeCycle = "3:00am"}
			else if (changeCycle == 13)
        {changeCycle = "3:15am"}
			else if (changeCycle == 14)
        {changeCycle = "3:30am"}
			else if (changeCycle == 15)
        {changeCycle = "3:45am"}
			else if (changeCycle == 16)
        {changeCycle = "4:00am"}
			else if (changeCycle == 17)
        {changeCycle = "4:15am"}
			else if (changeCycle == 18)
        {changeCycle = "4:30am"}
			else if (changeCycle == 19)
        {changeCycle = "4:45am"}
			else if (changeCycle == 20)
        {changeCycle = "5:00am"}
			else if (changeCycle == 21)
        {changeCycle = "5:15am"}
			else if (changeCycle == 22)
        {changeCycle = "5:30am"}
			else if (changeCycle == 23)
        {changeCycle = "5:45am"}
			else if (changeCycle == 24)
        {changeCycle = "6:00am"}
			else if (changeCycle == 25)
        {changeCycle = "6:15am"}
			else if (changeCycle == 26)
        {changeCycle = "6:30am"}
			else if (changeCycle == 27)
        {changeCycle = "6:45am"}
			else if (changeCycle == 28)
        {changeCycle = "7:00am"}
			else if (changeCycle == 29)
        {changeCycle = "7:15am"}
			else if (changeCycle == 30)
        {changeCycle = "7:30am"}
			else if (changeCycle == 31)
        {changeCycle = "7:45am"}
			else if (changeCycle == 32)
        {changeCycle = "8:00am"}
			else if (changeCycle == 33)
        {changeCycle = "8:15am"}
			else if (changeCycle == 34)
        {changeCycle = "8:30am"}
			else if (changeCycle == 35)
        {changeCycle = "8:45am"}
			else if (changeCycle == 36)
        {changeCycle = "9:00am"}
			else if (changeCycle == 37)
        {changeCycle = "9:15am"}
			else if (changeCycle == 38)
        {changeCycle = "9:30am"}
			else if (changeCycle == 39)
        {changeCycle = "9:45am"}
			else if (changeCycle == 40)
        {changeCycle = "10:00am"}
			else if (changeCycle == 41)
        {changeCycle = "10:15am"}
			else if (changeCycle == 42)
        {changeCycle = "10:30am"}
			else if (changeCycle == 43)
        {changeCycle = "10:45am"}
			else if (changeCycle == 44)
        {changeCycle = "11:00am"}
			else if (changeCycle == 45)
        {changeCycle = "11:15am"}
			else if (changeCycle == 46)
        {changeCycle = "11:30am"}
			else if (changeCycle == 47)
        {changeCycle = "11:45am"}
			else if (changeCycle == 48)
        {changeCycle = "12:00pm"}
        	else if (changeCycle == 49)
		(changeCycle = "12:15pm")
			else if (changeCycle == 50)
        {changeCycle = "12:30pm"}
			else if (changeCycle == 51)
        {changeCycle = "12:45pm"}
			else if (changeCycle == 52)
        {changeCycle = "1:00pm"}
			else if (changeCycle == 53)
        {changeCycle = "1:15pm"}
			else if (changeCycle == 54)
        {changeCycle = "1:30pm"}
            else if (changeCycle == 55)
        {changeCycle = "1:45pm"}
			else if (changeCycle == 56)
        {changeCycle = "2:00pm"}
			else if (changeCycle == 57)
        {changeCycle = "2:15pm"}
			else if (changeCycle == 58)
        {changeCycle = "2:30pm"}
			else if (changeCycle == 59)
        {changeCycle = "2:45pm"}
			else if (changeCycle == 60)
        {changeCycle = "3:00pm"}
			else if (changeCycle == 61)
        {changeCycle = "3:15pm"}
			else if (changeCycle == 62)
        {changeCycle = "3:30pm"}
			else if (changeCycle == 63)
        {changeCycle = "3:45pm"}
			else if (changeCycle == 64)
        {changeCycle = "4:00pm"}
			else if (changeCycle == 65)
        {changeCycle = "4:15pm"}
			else if (changeCycle == 66)
        {changeCycle = "4:30pm"}
			else if (changeCycle == 67)
        {changeCycle = "4:45pm"}
			else if (changeCycle == 68)
        {changeCycle = "5:00pm"}
			else if (changeCycle == 69)
        {changeCycle = "5:15pm"}
			else if (changeCycle == 70)
        {changeCycle = "5:30pm"}
			else if (changeCycle == 71)
        {changeCycle = "5:45pm"}
        	else if (changeCycle == 72)
        {changeCycle = "6:00pm"}
			else if (changeCycle == 73)
        {changeCycle = "6:15pm"}
			else if (changeCycle == 74)
        {changeCycle = "6:30pm"}
			else if (changeCycle == 75)
        {changeCycle = "6:45pm"}
			else if (changeCycle == 76)
        {changeCycle = "7:00pm"}
			else if (changeCycle == 77)
        {changeCycle = "7:15pm"}
			else if (changeCycle == 78)
        {changeCycle = "7:30pm"}
			else if (changeCycle == 79)
        {changeCycle = "7:45pm"}
			else if (changeCycle == 80)
        {changeCycle = "8:00pm"}
			else if (changeCycle == 81)
        {changeCycle = "8:15pm"}
			else if (changeCycle == 82)
        {changeCycle = "8:30pm"}
			else if (changeCycle == 83)
        {changeCycle = "8:45pm"}
			else if (changeCycle == 84)
        {changeCycle = "9:00pm"}
			else if (changeCycle == 85)
        {changeCycle = "9:15pm"}
			else if (changeCycle == 86)
        {changeCycle = "9:30pm"}
			else if (changeCycle == 87)
        {changeCycle = "9:45pm"}
			else if (changeCycle == 88)
        {changeCycle = "10:00pm"}
			else if (changeCycle == 89)
        {changeCycle = "10:15pm"}
			else if (changeCycle == 90)
        {changeCycle = "10:30pm"}
			else if (changeCycle == 91)
        {changeCycle = "10:45pm"}
			else if (changeCycle == 92)
        {changeCycle = "11:00pm"}
			else if (changeCycle == 93)
        {changeCycle = "11:15pm"}
			else if (changeCycle == 94)
        {changeCycle = "11:30pm"}
			else if (changeCycle == 95)
        {changeCycle = "11:45pm"}
			else if (changeCycle == 96)
        {changeCycle = "12:00am"}
        	else {
      			changeCycle = "N/A"
        }
        
        
        log.debug curTemp
        log.debug fanMode
        log.debug switchPos
        log.debug emergencyHeat
        log.debug temporaryHoldUntilTime
        
        
       
        //fan mode 0=auto, 2=circ, 1=on
        
        if(fanMode==0)
          fanMode = 'auto'
        if(fanMode==1)
          fanMode = 'on'
        if(fanMode==2)
          fanMode = 'circ'
        
        if(switchPos==0)
          switchPos = 'emerg'
        if(switchPos==1)
          switchPos = 'heat'
        if(switchPos==2)
          switchPos = 'off'
        if(switchPos==3)
          switchPos = 'cool'
        if(switchPos==4)
          switchPos = 'auto'

	//Send events 
        sendEvent(name: 'thermostatOperatingState', value: operatingState)
        sendEvent(name: 'thermostatFanMode', value: fanMode)
        sendEvent(name: 'thermostatMode', value: switchPos)
        sendEvent(name: 'coolingSetpoint', value: coolSetPoint as Integer)
        sendEvent(name: 'heatingSetpoint', value: heatSetPoint as Integer)
        sendEvent(name: 'temperature', value: curTemp as Integer, state: switchPos)
        sendEvent(name: 'relativeHumidity', value: curHumidity as Integer)
        sendEvent(name: 'changeCycle', value: changeCycle)
        sendEvent(name: 'outdoorTemp', value: outdoorTemp as Integer)
        sendEvent(name: 'outdoorHumid', value: outdoorHumid as Integer)
        
        
 
    }

}

def api(method, args = [], success = {}) {

}

// Need to be logged in before this is called. So don't call this. Call api.
def doRequest(uri, args, type, success) {

}

def refresh() {
  log.debug "Executing 'refresh'"
    login()
    getStatus()
}

def login() {  
  log.debug "Executing 'login'"
      

        
    def params = [
        uri: 'https://mytotalconnectcomfort.com/portal',
        headers: [
            'Content-Type': 'application/x-www-form-urlencoded',
            'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8',
            'Accept-Encoding': 'sdch',
            'Host': 'mytotalconnectcomfort.com',
            'DNT': '1',
            'Origin': 'mytotalconnectcomfort.com/portal/',
            'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36'
        ],
        body: [timeOffset: '240', UserName: "${settings.username}", Password: "${settings.password}", RememberMe: 'false']
    ]

  data.cookiess = ''

    httpPost(params) { response ->
        log.debug "Request was successful, $response.status"
        log.debug response.headers
    response.getHeaders('Set-Cookie').each {
          String cookie = it.value.split(';|,')[0]
      log.debug "Adding cookie to collection: $cookie"
            if(cookie != ".ASPXAUTH_TH_A=") {
      data.cookiess = data.cookiess+cookie+';'
            }
        }
        log.debug "cookies: $data.cookiess"

    }
    

}

def isLoggedIn() {
    if(!data.auth) {
        log.debug "No data.auth"
        return false
    }
    
    def now = new Date().getTime();
    return data.auth.expires_in > now
}
