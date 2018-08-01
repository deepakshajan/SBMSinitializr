class Helper {

    static modifyState(data, state) {

        let newState = state;
        let dataObj = JSON.parse(data);
        let key = Object.keys(dataObj)[0];
        let value = dataObj[key];
        if(key === "completed") {
            newState.process[key].push(value);
            newState.process.starting.pop(value);
            let progressValue = Helper.calculateProgressValue(newState);
            newState.progressBar.value = progressValue;
        } else if(key === "starting") {
            newState.process[key].push(value);
        } else if(key === "toBeStarted") {
            newState.process[key].push(value);
        } else if(key === "stopped") {
            newState.process.toBeStarted.pop(value);
            newState.process.starting.pop(value);
            newState.process.completed.pop(value);
            let progressValue = Helper.calculateProgressValue(newState);
            newState.progressBar.value = progressValue;
        } else if(key === "failed") {
            newState.process.failed.push(value);
            newState.process.starting.pop(value);
        } else if(key === "/initializr/getProcessTree") {
            value = value.replace('"{', '{');
            value = value.replace('}"', '}');
            value = value.split('\\"').join('"');
            let valueObj = JSON.parse(value);
            newState.process.processTree = valueObj;
        }
        return newState;
    }

    static calculateProgressValue(state) {

        let toBeStartedProcessCount = state.process.toBeStarted.length;
        let completedProcessCount = state.process.completed.length;
        let completionPercentage = toBeStartedProcessCount !==0 ? Math.floor((completedProcessCount/toBeStartedProcessCount)*100) : 0;
        return completionPercentage;
    }

}

export default Helper;