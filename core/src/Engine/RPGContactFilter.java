package Engine;

import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;

public class RPGContactFilter implements ContactFilter {

	RPGWorld world;
	 public RPGContactFilter(RPGWorld world) {
		this.world = world;
	}
	
	@Override
	public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
		Filter filterA = fixtureA.getFilterData();
		Filter filterB = fixtureB.getFilterData();
		if(filterA.categoryBits == filterB.categoryBits)
			return false;
		if(filterA.categoryBits != RPGWorld.CATEGORY_SCENERY && filterB.categoryBits != RPGWorld.CATEGORY_SCENERY)
		{	
			ObjectData fixAData = recreate(fixtureA);
		ObjectData fixBData = recreate(fixtureB);
		
			if(fixAData.isInvisible || fixBData.isInvisible)
			return false;
		
	
		if((filterA.categoryBits == RPGWorld.CATEGORY_PLAYER && filterB.categoryBits == RPGWorld.CATEGORY_BULLET) || (filterA.categoryBits == RPGWorld.CATEGORY_BULLET && filterB.categoryBits == RPGWorld.CATEGORY_PLAYER))
			return true;
		
		}
	return true;
	
	}
	
	private ObjectData recreate(Fixture fixtureA) {
		if(fixtureA.getUserData().getClass().getName() == "com.badlogic.gdx.physics.box2d.Fixture" ) {
			Fixture temp = (Fixture) fixtureA.getUserData();
			return (ObjectData) temp.getUserData();
		}
		return (ObjectData) fixtureA.getUserData();
	}
	
}